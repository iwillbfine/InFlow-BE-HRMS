package com.pado.inflow.evaluation.command;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.evaluation.command.application.service.TaskEvalServiceImpl;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskEvalResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskItemEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskEvalEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskEvalRepository;
import com.pado.inflow.evaluation.command.domain.repository.TaskItemRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationMapper;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import com.pado.inflow.evaluation.query.repository.TaskEvalMapper;
import com.pado.inflow.evaluation.query.service.EvaluationPolicyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskEvalServiceImplTests {

    @InjectMocks
    private TaskEvalServiceImpl taskEvalService;

    @Mock
    private TaskEvalRepository taskEvalRepository;

    @Mock
    private TaskItemRepository taskItemRepository;

    @Mock
    private EvaluationMapper evaluationMapper;

    @Mock
    private EvaluationPolicyMapper evaluationPolicyMapper;

    @Mock
    private EvaluationRepository evaluationRepository;

    @Mock
    private TaskEvalMapper taskEvalMapper;

    @Mock
    private EvaluationPolicyService evaluationPolicyService;

    @Test
    @DisplayName("과제 평가 생성 성공 테스트")
    void createTaskEval_Success() {
        // given
        CreateTaskEvalRequestDTO requestDTO = CreateTaskEvalRequestDTO.builder()
                .taskEvalName("Self Evaluation")
                .taskEvalContent("Evaluate your task completion")
                .score(90.0)
                .setRatio(0.1)
                .performanceInput("Great performance")
                .taskItemId(1L)
                .taskTypeId(2L)
                .build();

        EvaluationPolicyDTO policyDTO = new EvaluationPolicyDTO();
        policyDTO.setStartDate(LocalDateTime.now().minusDays(1));
        policyDTO.setEndDate(LocalDateTime.now().plusDays(1));
        policyDTO.setModifiableDate(LocalDateTime.now().plusDays(3));

        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationDTO.setEvaluationId(1L);
        evaluationDTO.setEvaluatorId(7L);

        TaskItemEntity taskItemEntity = TaskItemEntity.builder()
                .taskItemId(1L)
                .taskName("Sample Task")
                .build();

        when(evaluationPolicyMapper.findPolicyByYearAndHalf(2023, "1st"))
                .thenReturn(List.of(policyDTO));
        when(evaluationMapper.getEvaluationsByEmpIdAndYearAndHalf(7L, 2023, "1st"))
                .thenReturn(List.of(evaluationDTO));
        when(taskItemRepository.findById(1L)).thenReturn(Optional.of(taskItemEntity));
        when(taskEvalRepository.save(any(TaskEvalEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        TaskEvalResponseDTO responseDTO = taskEvalService.createTaskEval(requestDTO, 2023, "1st", 7L);

        // then
        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getTaskEvalName()).isEqualTo("Self Evaluation");
        assertThat(responseDTO.getScore()).isEqualTo(90.0);
        assertThat(responseDTO.getEvaluationId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("평가 기간이 아닌 경우 실패 테스트")
    void createTaskEval_Fail_NotInEvaluationPeriod() {
        // given
        CreateTaskEvalRequestDTO requestDTO = CreateTaskEvalRequestDTO.builder()
                .taskEvalName("Self Evaluation")
                .taskEvalContent("Evaluate your task completion")
                .score(90.0)
                .setRatio(0.1)
                .performanceInput("Great performance")
                .taskItemId(1L)
                .taskTypeId(2L)
                .build();

        EvaluationPolicyDTO policyDTO = new EvaluationPolicyDTO();
        policyDTO.setStartDate(LocalDateTime.now().plusDays(1));
        policyDTO.setEndDate(LocalDateTime.now().plusDays(2));

        when(evaluationPolicyMapper.findPolicyByYearAndHalf(2023, "1st"))
                .thenReturn(List.of(policyDTO));

        // when & then
        assertThrows(CommonException.class, () ->
                taskEvalService.createTaskEval(requestDTO, 2023, "1st", 7L)
        );
    }

    @Test
    @DisplayName("과제 항목을 찾을 수 없는 경우 실패 테스트")
    void createTaskEval_Fail_TaskItemNotFound() {
        // given
        CreateTaskEvalRequestDTO requestDTO = CreateTaskEvalRequestDTO.builder()
                .taskEvalName("Self Evaluation")
                .taskEvalContent("Evaluate your task completion")
                .score(90.0)
                .setRatio(0.1)
                .performanceInput("Great performance")
                .taskItemId(1L)
                .taskTypeId(2L)
                .build();

        EvaluationPolicyDTO policyDTO = new EvaluationPolicyDTO();
        policyDTO.setStartDate(LocalDateTime.now().minusDays(1));
        policyDTO.setEndDate(LocalDateTime.now().plusDays(1));

        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationDTO.setEvaluationId(1L);
        evaluationDTO.setEvaluatorId(7L);

        when(evaluationPolicyMapper.findPolicyByYearAndHalf(2023, "1st"))
                .thenReturn(List.of(policyDTO));
        when(evaluationMapper.getEvaluationsByEmpIdAndYearAndHalf(7L, 2023, "1st"))
                .thenReturn(List.of(evaluationDTO));
        when(taskItemRepository.findById(1L)).thenReturn(Optional.empty());     // 과제 항목 Empty로 리턴하도록 설정

        // when & then
        assertThrows(CommonException.class, () ->
                taskEvalService.createTaskEval(requestDTO, 2023, "1st", 7L)
        );
    }

    @Test
    @DisplayName("과제 평가 수정 성공 테스트")
    void updateTaskEval_Success() {
        // given
        Long taskEvalId = 1L;
        Long evaluationId = 2L;

        UpdateTaskEvalRequestDTO requestDTO = UpdateTaskEvalRequestDTO.builder()
                .taskEvalName("Updated Evaluation")
                .taskEvalContent("Updated Content")
                .score(95.0)
                .setRatio(0.3)
                .performanceInput("Improved Performance")
                .taskTypeId(3L)
                .taskItemId(4L)
                .evaluationId(evaluationId)
                .build();

        TaskEvalEntity existingTaskEvalEntity = TaskEvalEntity.builder()
                .taskEvalId(taskEvalId)
                .taskEvalName("Old Evaluation")
                .taskEvalContent("Old Content")
                .evaluationId(evaluationId)
                .build();

        EvaluationEntity evaluationEntity = EvaluationEntity.builder()
                .evaluationId(evaluationId)
                .year(2023)
                .half("1st")
                .build();

        EvaluationPolicyDTO evaluationPolicyDTO = EvaluationPolicyDTO.builder()
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

//        when(taskEvalMapper.findTaskEvalByTaskEvalId(taskEvalId)).thenReturn(existingTaskEvalEntity.toResponseDTO());
        when(evaluationRepository.findById(evaluationId)).thenReturn(Optional.of(evaluationEntity));
        when(evaluationPolicyService.findPolicyByYearAndHalfAndTaskTypeId(evaluationEntity.getYear(), evaluationEntity.getHalf(), requestDTO.getTaskTypeId()))
                .thenReturn(evaluationPolicyDTO);
        when(taskEvalRepository.save(any(TaskEvalEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        TaskEvalResponseDTO responseDTO = taskEvalService.updateTaskEval(taskEvalId, requestDTO);

        // then
        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getTaskEvalName()).isEqualTo("Updated Evaluation");
        assertThat(responseDTO.getTaskEvalContent()).isEqualTo("Updated Content");
        assertThat(responseDTO.getScore()).isEqualTo(95.0);
    }

    @Test
    @DisplayName("평가 기간 외 수정 실패 테스트")
    void updateTaskEval_Fail_NotInEvaluationPeriod() {
        // given
        Long taskEvalId = 1L;
        Long evaluationId = 2L;

        UpdateTaskEvalRequestDTO requestDTO = UpdateTaskEvalRequestDTO.builder()
                .taskEvalName("Updated Evaluation")
                .evaluationId(evaluationId)
                .build();

        TaskEvalEntity existingTaskEvalEntity = TaskEvalEntity.builder()
                .taskEvalId(taskEvalId)
                .evaluationId(evaluationId)
                .build();

        EvaluationEntity evaluationEntity = EvaluationEntity.builder()
                .evaluationId(evaluationId)
                .year(2023)
                .half("1st")
                .build();

        EvaluationPolicyDTO evaluationPolicyDTO = EvaluationPolicyDTO.builder()
                .startDate(LocalDateTime.now().plusDays(1))
                .endDate(LocalDateTime.now().plusDays(2))
                .build();

        when(taskEvalMapper.findTaskEvalByTaskEvalId(taskEvalId)).thenReturn(existingTaskEvalEntity.toDTO());
        when(evaluationRepository.findById(evaluationId)).thenReturn(Optional.of(evaluationEntity));
        when(evaluationPolicyService.findPolicyByYearAndHalfAndTaskTypeId(evaluationEntity.getYear(), evaluationEntity.getHalf(), requestDTO.getTaskTypeId()))
                .thenReturn(evaluationPolicyDTO);

        // when & then
        assertThrows(CommonException.class, () -> taskEvalService.updateTaskEval(taskEvalId, requestDTO),
                "평가 기간 외 수정 시 예외가 발생해야 합니다.");
    }

    @Test
    @DisplayName("존재하지 않는 평가 수정 실패 테스트")
    void updateTaskEval_Fail_EvaluationNotFound() {
        // given
        Long taskEvalId = 1L;
        Long evaluationId = 2L;

        UpdateTaskEvalRequestDTO requestDTO = UpdateTaskEvalRequestDTO.builder()
                .taskEvalName("Updated Evaluation")
                .evaluationId(evaluationId)
                .build();

        when(taskEvalMapper.findTaskEvalByTaskEvalId(taskEvalId)).thenReturn(null);

        // when & then
        assertThrows(CommonException.class, () -> taskEvalService.updateTaskEval(taskEvalId, requestDTO),
                "존재하지 않는 과제 평가를 수정할 때 예외가 발생해야 합니다.");
    }
}
