package com.pado.inflow.evaluation.command;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.evaluation.command.application.service.GradeServiceImpl;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateGradeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.GradeResponseDTO;
import com.pado.inflow.evaluation.command.domain.repository.GradeRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.dto.GradeDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GradeServiceTest {

    @InjectMocks
    private GradeServiceImpl gradeService;

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private EvaluationPolicyMapper evaluationPolicyMapper;

    @Mock
    private com.pado.inflow.evaluation.query.service.GradeService gradeQueryService;

    @Test
    @DisplayName("S등급 최초 생성 성공 테스트")
    void createFirstGrade_Success() {
        // given
        CreateGradeRequestDTO requestDTO = CreateGradeRequestDTO.builder()
                .gradeName("S")
                .startRatio(0.0)
                .endRatio(0.05)
                .absoluteGradeRatio(95.0)
                .build();

        EvaluationPolicyDTO policyDTO = new EvaluationPolicyDTO();
        policyDTO.setEvaluationPolicyId(1L);

        when(evaluationPolicyMapper.findPolicyByYearAndHalf(2023, "1st"))
                .thenReturn(Arrays.asList(policyDTO));
        when(gradeQueryService.findByEvaluationPolicyId(1L))
                .thenReturn(new ArrayList<>());
        when(gradeRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        List<GradeResponseDTO> result = gradeService.createGrade(requestDTO, 2023, "1st");

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGradeName()).isEqualTo("S");
        assertThat(result.get(0).getStartRatio()).isEqualTo(0.0);
        assertThat(result.get(0).getEndRatio()).isEqualTo(0.05);
    }

    @Test
    @DisplayName("A등급 생성 성공 테스트 (S등급 이후)")
    void createSecondGrade_Success() {
        // given
        CreateGradeRequestDTO requestDTO = CreateGradeRequestDTO.builder()
                .gradeName("A")
                .startRatio(0.06)
                .endRatio(0.15)
                .absoluteGradeRatio(90.0)
                .build();

        EvaluationPolicyDTO policyDTO = new EvaluationPolicyDTO();
        policyDTO.setEvaluationPolicyId(1L);

        GradeDTO existingGrade = new GradeDTO();
        existingGrade.setGradeName("S");
        existingGrade.setStartRatio(0.0);
        existingGrade.setEndRatio(0.05);
        existingGrade.setAbsoluteGradeRatio(95.0);

        when(evaluationPolicyMapper.findPolicyByYearAndHalf(2023, "1st"))
                .thenReturn(Arrays.asList(policyDTO));
        when(gradeQueryService.findByEvaluationPolicyId(1L))
                .thenReturn(Arrays.asList(existingGrade));
        when(gradeRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        List<GradeResponseDTO> result = gradeService.createGrade(requestDTO, 2023, "1st");

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGradeName()).isEqualTo("A");
        assertThat(result.get(0).getStartRatio()).isEqualTo(0.06);
        assertThat(result.get(0).getEndRatio()).isEqualTo(0.15);
    }

    @Test
    @DisplayName("절대평가 점수 순서 검증 실패 테스트")
    void createGrade_InvalidScore_Fail() {
        // given
        CreateGradeRequestDTO requestDTO = CreateGradeRequestDTO.builder()
                .gradeName("A")
                .startRatio(0.05)
                .endRatio(0.15)
                .absoluteGradeRatio(96.0)  // S등급 점수(95.0)보다 높음
                .build();

        EvaluationPolicyDTO policyDTO = new EvaluationPolicyDTO();
        policyDTO.setEvaluationPolicyId(1L);

        GradeDTO existingGrade = new GradeDTO();
        existingGrade.setGradeName("S");
        existingGrade.setStartRatio(0.0);
        existingGrade.setEndRatio(0.05);
        existingGrade.setAbsoluteGradeRatio(95.0);

        when(evaluationPolicyMapper.findPolicyByYearAndHalf(2023, "1st"))
                .thenReturn(Arrays.asList(policyDTO));
        when(gradeQueryService.findByEvaluationPolicyId(1L))
                .thenReturn(Arrays.asList(existingGrade));

        // when & then
        assertThrows(CommonException.class, () ->
                gradeService.createGrade(requestDTO, 2023, "1st")
        );
    }
}