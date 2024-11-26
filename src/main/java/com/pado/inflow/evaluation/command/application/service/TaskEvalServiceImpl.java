package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskEvalResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskItemEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskEvalEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskEvalRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskItemRepository; // 추가
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationMapper;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import com.pado.inflow.evaluation.query.repository.TaskEvalMapper;
import com.pado.inflow.evaluation.query.service.EvaluationPolicyService;
import com.pado.inflow.evaluation.query.service.EvaluationPolicyServiceImpl;
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
    private final EvaluationRepository evaluationRepository;
    private final EvaluationPolicyServiceImpl evaluationPolicyServiceImpl;
    private final EvaluationPolicyService evaluationPolicyService;

    public TaskEvalServiceImpl(TaskEvalRepository taskEvalRepository, TaskItemRepository taskItemRepository, EvaluationMapper evaluationMapper, EvaluationPolicyMapper evaluationPolicyMapper, TaskEvalMapper taskEvalMapper, EvaluationRepository evaluationRepository, EvaluationPolicyServiceImpl evaluationPolicyServiceImpl, EvaluationPolicyService evaluationPolicyService) {
        this.taskEvalRepository = taskEvalRepository;
        this.taskItemRepository = taskItemRepository;
        this.evaluationMapper = evaluationMapper;
        this.evaluationPolicyMapper = evaluationPolicyMapper;
        this.taskEvalMapper = taskEvalMapper;
        this.evaluationRepository = evaluationRepository;
        this.evaluationPolicyServiceImpl = evaluationPolicyServiceImpl;
        this.evaluationPolicyService = evaluationPolicyService;
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

    @Override
    public TaskEvalResponseDTO updateTaskEval(Long taskEvalId, UpdateTaskEvalRequestDTO requestDTO) {
        // 1. taskEvalId로 평가를 조회한다.
        TaskEvalDTO existingTaskEval = taskEvalMapper.findTaskEvalByTaskEvalId(taskEvalId);

        // 2. evaluationId로 평가 직접 조회
        Long evaluationId = requestDTO.getEvaluationId();
        EvaluationEntity evaluationEntity = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EVALUATION));

        // 3. 평가에서 year, half, task_type_id를 이용하여 평가 정책을 조회
        EvaluationPolicyDTO evaluationPolicyDTO = evaluationPolicyService.findPolicyByYearAndHalfAndTaskTypeId(
                evaluationEntity.getYear(), evaluationEntity.getHalf(), requestDTO.getTaskTypeId());

        // 4. 평가 정책의 시작일과 종료일을 통해 평가 기간을 검증
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(evaluationPolicyDTO.getStartDate()) || now.isAfter(evaluationPolicyDTO.getEndDate())) {
            throw new CommonException(ErrorCode.INVALID_MODIFIABLE_DATE);
        }

        // 수정 가능한 경우 데이터 업데이트
        existingTaskEval.setTaskEvalName(requestDTO.getTaskEvalName());
        existingTaskEval.setTaskEvalContent(requestDTO.getTaskEvalContent());
        existingTaskEval.setScore(requestDTO.getScore());
        existingTaskEval.setSetRatio(requestDTO.getSetRatio());
        existingTaskEval.setPerformanceInput(requestDTO.getPerformanceInput());
        existingTaskEval.setTaskTypeId(requestDTO.getTaskTypeId());
        existingTaskEval.setTaskItemId(requestDTO.getTaskItemId());

        TaskEvalEntity updatedEntity = taskEvalRepository.save(existingTaskEval.toEntity());

        // ResponseDTO 반환
        return updatedEntity.toResponseDTO();
    }

}

