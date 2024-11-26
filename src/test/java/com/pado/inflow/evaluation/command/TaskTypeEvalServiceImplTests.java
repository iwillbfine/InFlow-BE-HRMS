package com.pado.inflow.evaluation.command;

import com.pado.inflow.evaluation.command.application.service.TaskTypeEvalServiceImpl;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.dto.GradeDTO;
import com.pado.inflow.evaluation.query.dto.TaskTypeEvalDTO;
import com.pado.inflow.evaluation.query.service.EvaluationService;
import com.pado.inflow.evaluation.query.service.GradeService;
import com.pado.inflow.evaluation.query.service.TaskTypeEvalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskTypeEvalServiceImplTest {

    @InjectMocks
    private TaskTypeEvalServiceImpl taskTypeEvalService;

    @Mock
    private EvaluationService evaluationService;

    @Mock
    private TaskTypeEvalService taskTypeEvalQueryService;

    @Mock
    private EvaluationRepository evaluationRepository;

    @Mock
    private GradeService gradeService;

    @Test
    @DisplayName("두 명의 사원에 대한 평가 점수 계산 및 등급 부여 테스트")
    void calculateScoresAndAssignGrades() {
        // given
        Integer year = 2024;
        String half = "1st";

        // 1. 평가 데이터 준비
        List<EvaluationDTO> evaluations = Arrays.asList(
                EvaluationDTO.builder()
                        .evaluationId(1L)
                        .evaluationType("SELF")
                        .employeeId(1L)
                        .year(year)
                        .half(half)
                        .build(),
                EvaluationDTO.builder()
                        .evaluationId(2L)
                        .evaluationType("SELF")
                        .employeeId(2L)
                        .year(year)
                        .half(half)
                        .build()
        );

        // 2. TaskTypeEval 점수 데이터 준비
        List<TaskTypeEvalDTO> employee1Scores = Arrays.asList(
                createTaskTypeEvalDTO(1L, 90.0),
                createTaskTypeEvalDTO(2L, 85.0),
                createTaskTypeEvalDTO(3L, 88.0)
        );

        List<TaskTypeEvalDTO> employee2Scores = Arrays.asList(
                createTaskTypeEvalDTO(4L, 95.0),
                createTaskTypeEvalDTO(5L, 92.0),
                createTaskTypeEvalDTO(6L, 89.0)
        );

        // 3. 등급 기준 준비
        List<GradeDTO> grades = Arrays.asList(
                createGradeDTO("S", 0.0, 0.05),
                createGradeDTO("A", 0.05, 0.15),
                createGradeDTO("B", 0.15, 0.70)
        );

        // Mock 설정
        when(evaluationService.findAllEvaluations(year, half)).thenReturn(evaluations);
        when(taskTypeEvalQueryService.findByEvaluationId(1L)).thenReturn(employee1Scores);
        when(taskTypeEvalQueryService.findByEvaluationId(2L)).thenReturn(employee2Scores);
        when(gradeService.findGradeByYearAndHalf(year, half)).thenReturn(grades);

        EvaluationEntity savedEntity1 = createSavedEvaluationEntity(1L, 263.0, "B"); // 90 + 85 + 88
        EvaluationEntity savedEntity2 = createSavedEvaluationEntity(2L, 276.0, "A"); // 95 + 92 + 89

        when(evaluationRepository.findById(1L)).thenReturn(Optional.of(savedEntity1));
        when(evaluationRepository.findById(2L)).thenReturn(Optional.of(savedEntity2));

        // when
        taskTypeEvalService.updateAllUsersTaskTypeEvalAndEvaluationScoreAndGrade(year, half);

        // then
        assertAll(
                () -> assertEquals(263.0, savedEntity1.getFinScore()),
                () -> assertEquals("B", savedEntity1.getFinGrade()),
                () -> assertEquals(276.0, savedEntity2.getFinScore()),
                () -> assertEquals("A", savedEntity2.getFinGrade())
        );
    }

    @Test
    @DisplayName("평가 데이터가 없는 경우")
    void handleNoEvaluations() {
        // given
        when(evaluationService.findAllEvaluations(2024, "1st")).thenReturn(Collections.emptyList());

        // when
        taskTypeEvalService.updateAllUsersTaskTypeEvalAndEvaluationScoreAndGrade(2024, "1st");

        // then
        assertAll(
                () -> verify(taskTypeEvalQueryService, never()).findByEvaluationId(anyLong()),
                () -> verify(gradeService, never()).findGradeByYearAndHalf(anyInt(), anyString())
        );
    }

    // Helper methods
    private TaskTypeEvalDTO createTaskTypeEvalDTO(Long id, Double score) {
        return TaskTypeEvalDTO.builder()
                .taskTypeEvalId(id)
                .taskTypeTotalScore(score)
                .build();
    }

    private GradeDTO createGradeDTO(String name, Double start, Double end) {
        return GradeDTO.builder()
                .gradeName(name)
                .startRatio(start)
                .endRatio(end)
                .build();
    }

    private EvaluationEntity createSavedEvaluationEntity(Long id, Double score, String grade) {
        return EvaluationEntity.builder()
                .evaluationId(id)
                .finScore(score)
                .finGrade(grade)
                .build();
    }
}