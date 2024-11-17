package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.FeedbackDTO;
import com.pado.inflow.evaluation.query.repository.FeedbackMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class FeedbackServiceImplTest {

    @Autowired
    private  FeedbackMapper feedbackMapper;

    @Test
    @DisplayName("사원 피드백 조회 테스트")
    void findFeedbackTest() {

        //given
        FeedbackDTO testData = new FeedbackDTO();
        testData.setEvaluationId(2L);
        testData.setContent("한상민 사원님, 이번 평가에서 우수한 성적을 거두신 것을 진심으로 축하드립니다! 높은 책임감과 성실함으로 프로젝트를 성공적으로 이끌어주셔서 감사드립니다. 특히 어려운 상황에서도 포기하지 않고 끝까지 노력하는 모습이 매우 인상 깊었습니다. 앞으로도 팀의 든든한 버팀목이 되어주시리라 믿습니다. 더 큰 성과를 이뤄나가시기를 응원합니다!");

        //when
        FeedbackDTO selectedData = feedbackMapper.findEmpFeedback(7L, 2023, "1st");

        //then
        assertNotNull(selectedData);
        assertEquals(testData.getEvaluationId(), selectedData.getEvaluationId());
        assertEquals(testData.getContent(), selectedData.getContent());

    }
}