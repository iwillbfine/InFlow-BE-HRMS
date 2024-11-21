package com.pado.inflow.evaluation.command;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.evaluation.command.application.service.FeedBackService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateFeedbackRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class FeedbackServiceImplTests {

    @Autowired
    private FeedBackService feedBackService;

    @Test
    @DisplayName("피드백 생성 - 성공 테스트")
    void feedbackCreationSuccess() {
        // given

        CreateFeedbackRequestDTO duplicateFeedback = new CreateFeedbackRequestDTO();
        duplicateFeedback.setContent("중복 피드백 시도");
        duplicateFeedback.setEvaluationId(2L);  // 같은 평가 ID로 시도
        //when & then
        assertThrows(CommonException.class, () -> {
            feedBackService.createFeedback(duplicateFeedback);
        });

    }
}
