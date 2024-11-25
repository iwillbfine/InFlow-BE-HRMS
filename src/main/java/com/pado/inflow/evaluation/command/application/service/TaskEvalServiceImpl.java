package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskEvalResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskItemEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskEvalEntity;
import com.pado.inflow.evaluation.command.domain.repository.TaskEvalRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskItemRepository; // 추가
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationMapper;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import com.pado.inflow.evaluation.query.repository.TaskEvalMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("CommandtaskEvalServiceImpl")
@Transactional
public class TaskEvalServiceImpl implements TaskEvalService {

    private final TaskEvalRepository taskEvalRepository;
    private final TaskItemRepository taskItemRepository;
    private final EvaluationMapper evaluationMapper;
    private final EvaluationPolicyMapper evaluationPolicyMapper;
    private final TaskEvalMapper taskEvalMapper;

    public TaskEvalServiceImpl(TaskEvalRepository taskEvalRepository, TaskItemRepository taskItemRepository, EvaluationMapper evaluationMapper, EvaluationPolicyMapper evaluationPolicyMapper, TaskEvalMapper taskEvalMapper) {
        this.taskEvalRepository = taskEvalRepository;
        this.taskItemRepository = taskItemRepository;
        this.evaluationMapper = evaluationMapper;
        this.evaluationPolicyMapper = evaluationPolicyMapper;
        this.taskEvalMapper = taskEvalMapper;
    }

    @Override
    public TaskEvalResponseDTO createTaskEval(CreateTaskEvalRequestDTO createTaskEvalRequestDTO, Integer year, String half, Long employeeId) {
        // 평가 기간인지 확인
        List<EvaluationPolicyDTO> selectedPolicy =
                evaluationPolicyMapper.findPolicyByYearAndHalf(year, half);

        if (selectedPolicy.get(0).getStartDate().isAfter(LocalDateTime.now())
                || selectedPolicy.get(0).getEndDate().isBefore(LocalDateTime.now())) {
            throw new CommonException(ErrorCode.TASK_EVAL_CREATE_FAILURE);
        }

        // 평가 조회 후 Request 날린 employeeId와 평가 테이블에서 평가 대상자의 employeeId가 같으면 자기평가 아니면 리더평가
        List<EvaluationDTO> selectedEvaluations =
                evaluationMapper.getEvaluationsByEmpIdAndYearAndHalf(employeeId, year, half);

        // 자기평가와 리더평가 구분
        EvaluationDTO evaluationDTO = selectedEvaluations.stream()
                .filter(evaluation -> evaluation.getEvaluatorId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EVALUATION));

        // 과제 항목 조회 (TaskItemRepository 사용)
        Long taskItemId = createTaskEvalRequestDTO.getTaskItemId();
        TaskItemEntity taskItemEntity = taskItemRepository.findById(taskItemId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_TASK));

        // TaskEvalDTO 생성 후 엔티티로 변환하여 저장
        TaskEvalDTO taskEvalDTO = TaskEvalDTO.builder()
                .taskEvalName(createTaskEvalRequestDTO.getTaskEvalName())
                .taskEvalContent(createTaskEvalRequestDTO.getTaskEvalContent())
                .score(createTaskEvalRequestDTO.getScore())
                .setRatio(createTaskEvalRequestDTO.getSetRatio())
                .performanceInput(createTaskEvalRequestDTO.getPerformanceInput())
                .relEvalStatus(false)   // 기본값 false로 지정. 나중에 수정 필요
                .evaluationId(evaluationDTO.getEvaluationId())
                .modifiableDate(selectedPolicy.get(0).getModifiableDate()) // 수정 가능 날
                .taskTypeId(createTaskEvalRequestDTO.getTaskTypeId())
                .taskItemId(taskItemEntity.getTaskItemId())
                .build();

        // DTO를 엔티티로 변환
        TaskEvalEntity taskEvalEntity = taskEvalDTO.toEntity();

        TaskEvalEntity savedEntity = taskEvalRepository.save(taskEvalEntity);

        // ResponseDTO 생성 및 반환
        TaskEvalResponseDTO responseDTO = savedEntity.toResponseDTO();

        return responseDTO;
    }
}
