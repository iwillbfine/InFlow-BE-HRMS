package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EvaluationPolicyServiceImplTest {

    @Autowired
    private EvaluationPolicyMapper evaluationPolicyMapper;

    @Test
    @DisplayName("평가 정책 리스트 조회 테스트")
    void evaluationPoliciesFindTest() {

        // given
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setYear(2023);
        testData.setHalf("1st");

        //when
        List<EvaluationPolicyDTO> policyList = evaluationPolicyMapper.findPolicyByYearAndHalf(2023, "1st");

        // then
        assertNotNull(policyList);
        assertEquals(testData.getYear(), policyList.get(0).getYear());
        assertEquals(testData.getHalf(), policyList.get(0).getHalf());
    }

    @Test
    @DisplayName("평가 정책 단건 조회 테스트")
    void evaluationPolicyFindTest() {
        //given
        EvaluationPolicyDTO testData = new EvaluationPolicyDTO();
        testData.setEvaluationPolicyId(1L);

        //when
        EvaluationPolicyDTO selectedData = evaluationPolicyMapper.getEvaluationPolicyByEvaluationPolicyId(1L);

        //then
        assertEquals(testData.getEvaluationPolicyId(), selectedData.getEvaluationPolicyId());

    }
}