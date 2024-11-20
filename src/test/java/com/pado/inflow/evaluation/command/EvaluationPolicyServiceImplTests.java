package com.pado.inflow.evaluation.command;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.evaluation.command.application.service.EvaluationPolicyService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationPolicyRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import org.apache.ibatis.ognl.Evaluation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@SpringBootTest
@Transactional
class EvaluationPolicyServiceImplTests {

    @Autowired
    private EvaluationPolicyRepository evaluationPolicyRepository;

    @Autowired
    private EvaluationPolicyService evaluationPolicyService;

    @Test
    @DisplayName("평가 정책 생성 성공 테스트")
    void EvaluationPolicyCreationTest() {
        //given

        CreateEvaluationPolicyRequestDTO testDataDTO = new CreateEvaluationPolicyRequestDTO();
        testDataDTO.setStartDate(LocalDateTime.of(2023,6,01,00,00,00).withNano(0));
        testDataDTO.setEndDate(LocalDateTime.of(2023,7,30,00,00,00).withNano(0));
        testDataDTO.setYear(2024);
        testDataDTO.setHalf("1st");
        testDataDTO.setTaskRatio(1.0);
        testDataDTO.setMinRelEvalCount(20L);
        testDataDTO.setModifiableDate(LocalDateTime.now().withNano(0));
        testDataDTO.setPolicyDescription("테스트123");
        testDataDTO.setPolicyRegisterId(6L);
        testDataDTO.setTaskTypeId(1L);


        //when
        EvaluationPolicyEntity testDataEntity = testDataDTO.toEntity();
        evaluationPolicyRepository.save(testDataEntity);

        List<EvaluationPolicyEntity> selectedData =
                evaluationPolicyRepository.findEvaluationPolicyByYearAndHalf(2024, "1st");

        //then
        assertNotNull(testDataEntity);
        assertEquals(testDataDTO.getYear(), selectedData.get(0).getYear());
        assertEquals(testDataDTO.getHalf(), selectedData.get(0).getHalf());

            }
    @Test
    @DisplayName("평가 정책 생성 실패 테스트 - 동일 과제 유형 생성 실패")
    void evaluationPolicyCreationFailureTestAboutDuplicatedPolicy() {
        //given

        // 첫 번쨰 데이터 저장.
        CreateEvaluationPolicyRequestDTO testDataDTO = new CreateEvaluationPolicyRequestDTO();
        testDataDTO.setStartDate(LocalDateTime.of(2023,6,01,00,00,00).withNano(0));
        testDataDTO.setEndDate(LocalDateTime.of(2023,7,30,00,00,00).withNano(0));
        testDataDTO.setYear(2024);
        testDataDTO.setHalf("1st");
        testDataDTO.setTaskRatio(0.3);
        testDataDTO.setMinRelEvalCount(20L);
        testDataDTO.setModifiableDate(LocalDateTime.now().withNano(0));
        testDataDTO.setPolicyDescription("테스트123");
        testDataDTO.setPolicyRegisterId(6L);
        testDataDTO.setTaskTypeId(1L);

        EvaluationPolicyEntity testDataEntity = testDataDTO.toEntity();
        evaluationPolicyRepository.save(testDataEntity);

        // 예외를 던질 것으로 예상되는 두 번째 데이터 저장
        CreateEvaluationPolicyRequestDTO testDataDTO2 = new CreateEvaluationPolicyRequestDTO();
        testDataDTO2.setStartDate(LocalDateTime.of(2023,6,01,00,00,00).withNano(0));
        testDataDTO2.setEndDate(LocalDateTime.of(2023,7,30,00,00,00).withNano(0));
        testDataDTO2.setYear(2024);
        testDataDTO2.setHalf("1st");
        testDataDTO2.setTaskRatio(0.3);
        testDataDTO2.setMinRelEvalCount(20L);
        testDataDTO2.setModifiableDate(LocalDateTime.now().withNano(0));
        testDataDTO2.setPolicyDescription("테스트123");
        testDataDTO2.setPolicyRegisterId(6L);
        testDataDTO2.setTaskTypeId(1L);     // 동일 과제 유형 삽입 시 에러 발생

        //when & then

        assertThrows(CommonException.class, () -> {
            evaluationPolicyService.createEvaluationPolicy(testDataDTO2);
        });
    }

    @Test
    @DisplayName("평가 정책 생성 실패 테스트 - 과제 반영비율 합산 1.0 초과")
    void evaluationPolicyCreationFailureTestAboutExceedRatio() {
        //given

        CreateEvaluationPolicyRequestDTO testDataDTO = new CreateEvaluationPolicyRequestDTO();
        testDataDTO.setStartDate(LocalDateTime.of(2023,6,01,00,00,00).withNano(0));
        testDataDTO.setEndDate(LocalDateTime.of(2023,7,30,00,00,00).withNano(0));
        testDataDTO.setYear(2024);
        testDataDTO.setHalf("1st");
        testDataDTO.setTaskRatio(1.2);      // Ratio 합산 1.0 초과 시 에러 발생
        testDataDTO.setMinRelEvalCount(20L);
        testDataDTO.setModifiableDate(LocalDateTime.now().withNano(0));
        testDataDTO.setPolicyDescription("테스트123");
        testDataDTO.setPolicyRegisterId(6L);
        testDataDTO.setTaskTypeId(1L);

        // when & then
        assertThrows(CommonException.class, () -> {
            evaluationPolicyService.createEvaluationPolicy(testDataDTO);
                });

    }

    @Test
    @DisplayName("평가 정책 수정 테스트 - 성공")
    void EvaluationPolicyUpdateTest() {
        //given

        // 기존 평가 정책 데이터
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setStartDate(LocalDateTime.now().plusDays(10));
        testData.setEndDate(LocalDateTime.now().plusDays(20));
        testData.setYear(2024);
        testData.setHalf("1st");
        testData.setTaskRatio(0.3);
        testData.setMinRelEvalCount(20L);
        testData.setModifiableDate(LocalDateTime.now().plusDays(5));
        testData.setCreatedAt(LocalDateTime.now().withNano(0));
        testData.setPolicyDescription("기존 정책");
        testData.setPolicyRegisterId(6L);
        testData.setTaskTypeId(1L);

        EvaluationPolicyEntity savedEntity = evaluationPolicyRepository.save(testData.toEntity());

        // 평가 정책 수정 요청 데이터

        EvaluationPolicyDTO updateRequest = new EvaluationPolicyDTO();
        updateRequest.setStartDate(LocalDateTime.now().plusDays(15));        // 시작일 비교 OK
        updateRequest.setEndDate(LocalDateTime.now().plusDays(25));         // 종료일 비교 OK
        updateRequest.setYear(2024);
        updateRequest.setHalf("1st");
        updateRequest.setTaskRatio(0.4);
        updateRequest.setMinRelEvalCount(20L);
        updateRequest.setModifiableDate(LocalDateTime.now().plusDays(5));
        updateRequest.setCreatedAt(LocalDateTime.now().withNano(0));
        updateRequest.setPolicyDescription("수정된 정책");
        updateRequest.setPolicyRegisterId(6L);
        updateRequest.setTaskTypeId(1L);


        //when
        EvaluationPolicyEntity updatedData = evaluationPolicyRepository.save(updateRequest.toEntity());


        //then
        assertNotNull(updatedData);
        assertEquals(updateRequest.getTaskRatio(), updatedData.getTaskRatio());
        assertEquals(updateRequest.getPolicyDescription(), updatedData.getPolicyDescription());

    }


}