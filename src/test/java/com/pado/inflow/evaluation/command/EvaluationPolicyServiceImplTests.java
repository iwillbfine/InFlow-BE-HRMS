package com.pado.inflow.evaluation.command;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.evaluation.command.application.service.EvaluationPolicyService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import com.pado.inflow.evaluation.command.domain.repository.EvaluationPolicyRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.service.TaskItemService;
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

    @Autowired
    private TaskItemService taskItemService;

    @Test
    @DisplayName("평가 정책 생성 성공 테스트")
    void EvaluationPolicyCreationTest() {
        //given

        CreateEvaluationPolicyRequestDTO testDataDTO = new CreateEvaluationPolicyRequestDTO();
        testDataDTO.setStartDate(LocalDateTime.of(2023, 6, 01, 00, 00, 00).withNano(0));
        testDataDTO.setEndDate(LocalDateTime.of(2023, 7, 30, 00, 00, 00).withNano(0));
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
        testDataDTO.setStartDate(LocalDateTime.of(2023, 6, 01, 00, 00, 00).withNano(0));
        testDataDTO.setEndDate(LocalDateTime.of(2023, 7, 30, 00, 00, 00).withNano(0));
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
        testDataDTO2.setStartDate(LocalDateTime.of(2023, 6, 01, 00, 00, 00).withNano(0));
        testDataDTO2.setEndDate(LocalDateTime.of(2023, 7, 30, 00, 00, 00).withNano(0));
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
        testDataDTO.setStartDate(LocalDateTime.of(2023, 6, 01, 00, 00, 00).withNano(0));
        testDataDTO.setEndDate(LocalDateTime.of(2023, 7, 30, 00, 00, 00).withNano(0));
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
    void evaluationPolicyUpdateTest() {
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
        updateRequest.setStartDate(LocalDateTime.now().plusDays(15));
        updateRequest.setEndDate(LocalDateTime.now().plusDays(25));
        updateRequest.setYear(2024);
        updateRequest.setHalf("1st");
        updateRequest.setTaskRatio(0.4);                                    // 등급 비교
        updateRequest.setMinRelEvalCount(20L);
        updateRequest.setModifiableDate(LocalDateTime.now().plusDays(5));
        updateRequest.setCreatedAt(LocalDateTime.now().withNano(0));
        updateRequest.setPolicyDescription("수정된 정책");                   // 정책 설명 비교
        updateRequest.setPolicyRegisterId(6L);
        updateRequest.setTaskTypeId(1L);

        //when
        EvaluationPolicyEntity updatedData = evaluationPolicyRepository.save(updateRequest.toEntity());

        //then
        assertNotNull(updatedData);
        assertEquals(updateRequest.getTaskRatio(), updatedData.getTaskRatio());
        assertEquals(updateRequest.getPolicyDescription(), updatedData.getPolicyDescription());

    }

    @Test
    @DisplayName("평가 정책 수정 실패 - 평가 시작일 오류 ")
    void evaluationPolicyUpdateFailureTestAboutStartDateError() {
        //given

        // 기존 평가 정책 데이터
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setEvaluationPolicyId(999L);
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

        evaluationPolicyRepository.save(testData.toEntity());

        // 평가 정책 수정 요청 데이터

        UpdateEvaluationPolicyRequestDTO updateRequest = new UpdateEvaluationPolicyRequestDTO();
        testData.setEvaluationPolicyId(999L);
        updateRequest.setStartDate(LocalDateTime.now().minusDays(15));        // 시작일 비교
        updateRequest.setEndDate(LocalDateTime.now().plusDays(25));
        updateRequest.setYear(2024);
        updateRequest.setHalf("1st");
        updateRequest.setTaskRatio(0.4);
        updateRequest.setMinRelEvalCount(20L);
        updateRequest.setModifiableDate(LocalDateTime.now().plusDays(5));
        updateRequest.setPolicyDescription("수정된 정책");

        //when & then
        assertThrows(CommonException.class, () -> {
            evaluationPolicyService.updateEvaluationPolicy(updateRequest, 999L);
        });
    }

    @Test
    @DisplayName("평가 정책 수정 실패 - 평가 종료일  오류 ")
    void evaluationPolicyUpdateFailureAboutEndDateError() {
        //given

        // 기존 평가 정책 데이터
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setEvaluationPolicyId(999L);
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

        evaluationPolicyRepository.save(testData.toEntity());

        // 평가 정책 수정 요청 데이터

        UpdateEvaluationPolicyRequestDTO updateRequest = new UpdateEvaluationPolicyRequestDTO();
        testData.setEvaluationPolicyId(999L);
        updateRequest.setStartDate(LocalDateTime.now().minusDays(0));
        updateRequest.setEndDate(LocalDateTime.now().minusDays(30));            // 종료일 비교
        updateRequest.setYear(2024);
        updateRequest.setHalf("1st");
        updateRequest.setTaskRatio(0.4);
        updateRequest.setMinRelEvalCount(20L);
        updateRequest.setModifiableDate(LocalDateTime.now().plusDays(5));
        updateRequest.setPolicyDescription("수정된 정책");

        //when & then
        assertThrows(CommonException.class, () -> {
            evaluationPolicyService.updateEvaluationPolicy(updateRequest, 999L);
        });
    }

    @Test
    @DisplayName("평가 정책 수정 실패 - 평가 시작일이 평가 종료일보다 미래인 오류 ")
    void evaluationPolicyUpdateFailureAboutStarDateOverEndDateError() {
        //given

        // 기존 평가 정책 데이터
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setEvaluationPolicyId(999L);
        testData.setStartDate(LocalDateTime.now().plusDays(10));
        testData.setEndDate(LocalDateTime.now().plusDays(10));
        testData.setYear(2024);
        testData.setHalf("1st");
        testData.setTaskRatio(0.3);
        testData.setMinRelEvalCount(20L);
        testData.setModifiableDate(LocalDateTime.now().plusDays(5));
        testData.setCreatedAt(LocalDateTime.now().withNano(0));
        testData.setPolicyDescription("기존 정책");
        testData.setPolicyRegisterId(6L);
        testData.setTaskTypeId(1L);

        evaluationPolicyRepository.save(testData.toEntity());

        // 평가 정책 수정 요청 데이터

        UpdateEvaluationPolicyRequestDTO updateRequest = new UpdateEvaluationPolicyRequestDTO();
        testData.setEvaluationPolicyId(999L);
        updateRequest.setStartDate(LocalDateTime.now().plusDays(30));       // 평가 시작일 Plus
        updateRequest.setEndDate(LocalDateTime.now().plusDays(0));
        updateRequest.setYear(2024);
        updateRequest.setHalf("1st");
        updateRequest.setTaskRatio(0.4);
        updateRequest.setMinRelEvalCount(20L);
        updateRequest.setModifiableDate(LocalDateTime.now().plusDays(5));
        updateRequest.setPolicyDescription("수정된 정책");

        //when & then
        assertThrows(CommonException.class, () -> {
            evaluationPolicyService.updateEvaluationPolicy(updateRequest, 999L);
        });
    }

    @Test
    @DisplayName("평가 정책 수정 실패 - 수정 가능 월이 Now()보다 과거인 오류 ")
    void evaluationPolicyUpdateFailureTestAboutModifiableDateIsPastNow() {
        //given

        // 기존 평가 정책 데이터
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setEvaluationPolicyId(999L);
        testData.setStartDate(LocalDateTime.now().plusDays(10));
        testData.setEndDate(LocalDateTime.now().plusDays(10));
        testData.setYear(2024);
        testData.setHalf("1st");
        testData.setTaskRatio(0.3);
        testData.setMinRelEvalCount(20L);
        testData.setModifiableDate(LocalDateTime.now().plusDays(5));
        testData.setCreatedAt(LocalDateTime.now().withNano(0));
        testData.setPolicyDescription("기존 정책");
        testData.setPolicyRegisterId(6L);
        testData.setTaskTypeId(1L);

        evaluationPolicyRepository.save(testData.toEntity());

        // 평가 정책 수정 요청 데이터

        UpdateEvaluationPolicyRequestDTO updateRequest = new UpdateEvaluationPolicyRequestDTO();
        testData.setEvaluationPolicyId(999L);
        updateRequest.setStartDate(LocalDateTime.now().plusDays(0));
        updateRequest.setEndDate(LocalDateTime.now().plusDays(0));
        updateRequest.setYear(2024);
        updateRequest.setHalf("1st");
        updateRequest.setTaskRatio(0.4);
        updateRequest.setMinRelEvalCount(20L);
        updateRequest.setModifiableDate(LocalDateTime.now().minusDays(10));     // 테스트를 위해 수정일을 now보다 더 과거로 설정
        updateRequest.setPolicyDescription("수정된 정책");

        //when & then
        assertThrows(CommonException.class, () -> {
            evaluationPolicyService.updateEvaluationPolicy(updateRequest, 999L);
        });
    }

    @Test
    @DisplayName("평가 정책 수정 실패 - 과제 반영 비율 합 1.0 초과")
    void evaluationPolicyUpdateFailureTestAboutExceededTaskRatio() {
        //given

        // 기존 평가 정책 데이터
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setEvaluationPolicyId(999L);
        testData.setStartDate(LocalDateTime.now().plusDays(10));
        testData.setEndDate(LocalDateTime.now().plusDays(10));
        testData.setYear(2024);
        testData.setHalf("1st");
        testData.setTaskRatio(0.3);
        testData.setMinRelEvalCount(20L);
        testData.setModifiableDate(LocalDateTime.now().plusDays(5));
        testData.setCreatedAt(LocalDateTime.now().withNano(0));
        testData.setPolicyDescription("기존 정책");
        testData.setPolicyRegisterId(6L);
        testData.setTaskTypeId(1L);

        evaluationPolicyRepository.save(testData.toEntity());

        EvaluationPolicyDTO testData2 = new EvaluationPolicyDTO();
        testData2.setEvaluationPolicyId(1000L);
        testData2.setStartDate(LocalDateTime.now().plusDays(10));
        testData2.setEndDate(LocalDateTime.now().plusDays(10));
        testData2.setYear(2024);
        testData2.setHalf("1st");
        testData2.setTaskRatio(0.6);
        testData2.setMinRelEvalCount(20L);
        testData2.setModifiableDate(LocalDateTime.now().plusDays(5));
        testData2.setCreatedAt(LocalDateTime.now().withNano(0));
        testData2.setPolicyDescription("기존 정책");
        testData2.setPolicyRegisterId(6L);
        testData2.setTaskTypeId(2L);                        // 에러 방지 위해 다른 과제 유형으로 수정

        evaluationPolicyRepository.save(testData2.toEntity());

        // 평가 정책 수정 요청 데이터

        UpdateEvaluationPolicyRequestDTO updateRequest = new UpdateEvaluationPolicyRequestDTO();
        testData.setEvaluationPolicyId(999L);
        updateRequest.setStartDate(LocalDateTime.now().plusDays(0));
        updateRequest.setEndDate(LocalDateTime.now().plusDays(0));
        updateRequest.setYear(2024);
        updateRequest.setHalf("1st");
        updateRequest.setTaskRatio(0.5);                // 에러 발생을 위해 합 1.1이 되도록 수정
        updateRequest.setMinRelEvalCount(20L);
        updateRequest.setModifiableDate(LocalDateTime.now().minusDays(0));
        updateRequest.setPolicyDescription("수정된 정책");

        //when & then
        assertThrows(CommonException.class, () -> {
            evaluationPolicyService.updateEvaluationPolicy(updateRequest, 999L);
        });
    }

    @Test
    @DisplayName("평가 정책 삭제 성공 테스트")
    void deleteEvaluationPolicySuccessTest() {
        // given
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setStartDate(LocalDateTime.now().plusDays(10));  // 미래 시작일
        testData.setEndDate(LocalDateTime.now().plusDays(20));
        testData.setYear(2024);
        testData.setHalf("1st");
        testData.setTaskRatio(0.3);
        testData.setMinRelEvalCount(20L);
        testData.setModifiableDate(LocalDateTime.now().plusDays(5));
        testData.setCreatedAt(LocalDateTime.now());
        testData.setPolicyDescription("삭제될 정책");
        testData.setPolicyRegisterId(6L);
        testData.setTaskTypeId(1L);

        EvaluationPolicyEntity savedEntity = evaluationPolicyRepository.save(testData.toEntity());

        // when
        evaluationPolicyService.deleteEvaluationPolicyByEvaluationPolicyId(savedEntity.getEvaluationPolicyId());

        // then
        assertThrows(CommonException.class, () -> {
            taskItemService.findTaskItems(savedEntity.getEvaluationPolicyId());
        });
    }

    @Test
    @DisplayName("평가 정책 삭제 실패 테스트 - 이미 시작된 정책")
    void deleteEvaluationPolicyFailureTestAlreadyStarted() {

    }

    @Test
    @DisplayName("평가 정책 삭제 실패 테스트 - 참조중인 과제 항목 존재")
    void deleteEvaluationPolicyFailureTestPolicyInUse() {

    }

    @Test
    @DisplayName("평가 정책 삭제 실패 테스트 - 존재하지 않는 정책")
    void deleteEvaluationPolicyFailureTestNotFound() {
        
    }
}