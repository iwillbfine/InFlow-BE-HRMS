package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateEvaluationRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationRepository;
import com.pado.inflow.evaluation.command.domain.repository.GradeRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskEvalRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskTypeEvalRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.dto.GradeDTO;
import com.pado.inflow.evaluation.query.dto.TaskTypeEvalDTO;
import com.pado.inflow.evaluation.query.service.EvaluationService;
import com.pado.inflow.evaluation.query.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("CommandTaskTypeEvalService")
@Transactional
public class TaskTypeEvalServiceImpl implements TaskTypeEvalService {


    private final EvaluationService evaluationService;
    private final TaskEvalRepository taskEvalRepository;
    private final TaskTypeEvalRepository taskTypeEvalRepository;
    private final EvaluationRepository evaluationRepository;
    private final GradeRepository gradeRepository;
    private final com.pado.inflow.evaluation.query.service.TaskTypeEvalService taskTypeEvalService;
    private final GradeService gradeService;

    public TaskTypeEvalServiceImpl(EvaluationService evaluationService
            , TaskEvalRepository taskEvalRepository
            , TaskTypeEvalRepository taskTypeEvalRepository
            , EvaluationRepository evaluationRepository
            , GradeRepository gradeRepository, com.pado.inflow.evaluation.query.service.TaskTypeEvalService taskTypeEvalService, GradeService gradeService) {

        this.evaluationService = evaluationService;
        this.taskEvalRepository = taskEvalRepository;
        this.taskTypeEvalRepository = taskTypeEvalRepository;
        this.evaluationRepository = evaluationRepository;
        this.gradeRepository = gradeRepository;
        this.taskTypeEvalService = taskTypeEvalService;
        this.gradeService = gradeService;
    }

    @Override
    public void updateAllUsersTaskTypeEvalAndEvaluationScoreAndGrade(Integer year, String half) {
        // 1. 해당 년도/반기의 모든 평가 조회
        List<EvaluationDTO> evaluations = evaluationService.findAllEvaluations(year, half);

        // 2. 자기평가/리더평가 분리
        Map<String, List<EvaluationDTO>> evaluationsByType = evaluations.stream()
                .collect(Collectors.groupingBy(EvaluationDTO::getEvaluationType));

        // 3. 각 평가 유형별 처리
        for (Map.Entry<String, List<EvaluationDTO>> entry : evaluationsByType.entrySet()) {
            // 3-1. 점수 업데이트를 위한 DTO 리스트 생성
            List<UpdateEvaluationRequestDTO> updateScoreDTOs = new ArrayList<>();

            // 3-2. 각 평가별 TaskTypeEval 점수 합산
            for (EvaluationDTO evaluation : entry.getValue()) {
                List<TaskTypeEvalDTO> taskTypeEvals = taskTypeEvalService
                        .findByEvaluationId(evaluation.getEvaluationId());

                double totalScore = taskTypeEvals.stream()
                        .mapToDouble(TaskTypeEvalDTO::getTaskTypeTotalScore)
                        .sum();

                updateScoreDTOs.add(UpdateEvaluationRequestDTO.builder()
                        .evaluationId(evaluation.getEvaluationId())
                        .finScore(totalScore)
                        .build());
            }

            // 3-3. 점수 기준 내림차순 정렬
            updateScoreDTOs.sort(Comparator.comparing(UpdateEvaluationRequestDTO::getFinScore).reversed());

            // 3-4. 등급 부여
            List<GradeDTO> grades = gradeService.findGradeByYearAndHalf(year, half);
            int totalCount = updateScoreDTOs.size();
            int currentIndex = 0;

            for (GradeDTO grade : grades) {
                int gradeCount = (int) Math.round(totalCount * (grade.getEndRatio() - grade.getStartRatio()));
                for (int i = 0; i < gradeCount && currentIndex < totalCount; i++) {
                    updateScoreDTOs.get(currentIndex++).setFinGrade(grade.getGradeName());
                }
            }

            if (currentIndex < totalCount && !grades.isEmpty()) {
                String lastGrade = grades.get(grades.size() - 1).getGradeName();
                while (currentIndex < totalCount) {
                    updateScoreDTOs.get(currentIndex++).setFinGrade(lastGrade);
                }
            }

            // 3-5. DB 업데이트
            for (UpdateEvaluationRequestDTO updateDTO : updateScoreDTOs) {
                EvaluationEntity evaluationEntity = evaluationRepository
                        .findById(updateDTO.getEvaluationId())
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EVALUATION));

                EvaluationEntity updatedEntity = EvaluationEntity.builder()
                        .evaluationId(evaluationEntity.getEvaluationId())
                        .evaluationType(evaluationEntity.getEvaluationType())
                        .finGrade(updateDTO.getFinGrade())
                        .finScore(updateDTO.getFinScore())
                        .year(evaluationEntity.getYear())
                        .half(evaluationEntity.getHalf())
                        .createdAt(evaluationEntity.getCreatedAt())
                        .evaluatorId(evaluationEntity.getEvaluatorId())
                        .employeeId(evaluationEntity.getEmployeeId())
                        .build();

                evaluationRepository.save(updatedEntity);
            }
        }
    }
}


/*
TaskTypeEval에 존재하는 각 평가ID를 찾아 평가 ID를 통해 과제항목별 평가를 조회한 후
각 과제 항목을 과제 유형별로 그룹화를 진행한다.
그 이후 그룹화가 되었으면 각 유형별로 총점을 계산해 평가 정책별 평가 테이블에 점수를 Insert 해야한다.
이 때 어떤 과제 유형에 대한 점수인지는 평가 정책을 조회하여
평가 정책의 과제유형Id를 통해 인식한다.

이렇게 점수 insert가 완료되었으면 각 row에 있는 평가ID를 통해 조회한 평가 Row에
과제 유형별 합산 점수를 Update 한다. ( 자기평가, 리더평가 2개의 Row에 점수 Update )

이렇게 점수 Update가 모두 완료되었으면
해당 년도 및 반기별 평가에 대한 상대평가를 진행한다.
평가는 그냥 모든 년도 및 반기에 대해서 한 줄로 줄세우자.

*/

// 각 평가 마다 평가에 연결된 과제 유형 List 조회 및 과제 유형별로 분류

// 각 과제 유형별 합산 점수를 계산하여 평가ID에 연결된 TaskTypeEval 테이블에 삽입 ( TaskTypeEval에서 어떤 과제 유형인지는 TaskTypeEval에 존재하는 evaluation_policy_id를 통해 과제유형Id를 확인

// TaskTypeEval에 과제 유형별로 모든 점수를 Update 했으면, 이제는 각 TaskTypeEval에 연결된 평가ID에 점수를 Update 해줘야함.( 평가정책별평가 n개가 평가ID 하나에 매핑되는 구조 - 정확히는 과제 유형 갯수 X 2만큼 사원당 TaskTypeEval row 존재 (자기평가, 리더평가 존재하므로))

// Evaluation에 TaskTypeEval에서 합산된 점수를 Update 했으면 이제 년도 및 반기별로 상대평가를 진행해야함

// 상대평가 진행시 사원의 원 점수를 일렬로 줄 세우고 평가 등급에 나와있는 상대평가 기준 비율 등급에 따라 점수별로 구간을 산정하여 최종 등급 부여.