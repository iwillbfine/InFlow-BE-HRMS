package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EvaluationServiceImplTest {

    @Autowired
    private EvaluationService evaluationService;

    @Test
    @DisplayName("사원별 최종 평가 등급 조회 테스트")
    void getFinalGradeTest() {
        //given
        EvaluationDTO testData = new EvaluationDTO();
        testData.setFinGrade("S");
        testData.setEvaluationType("리더평가");

        //when
        EvaluationDTO selectedData = evaluationService.findEvaluationGrade(7L, 2023, "1st");

        //then
        assertNotNull(selectedData);
        assertEquals(testData.getEvaluationType(), selectedData.getEvaluationType());
        assertEquals(testData.getFinGrade(), selectedData.getFinGrade());
    }

}