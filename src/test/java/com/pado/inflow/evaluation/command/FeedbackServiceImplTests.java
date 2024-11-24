package com.pado.inflow.evaluation.command;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.evaluation.command.application.service.FeedBackService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateFeedbackResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateFeedbackResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.FeedbackEntity;
import com.pado.inflow.evaluation.command.domain.repository.FeedbackRepository;
import com.pado.inflow.evaluation.query.dto.FeedbackDTO;
import com.pado.inflow.evaluation.query.repository.FeedbackMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class FeedbackServiceImplTests {

    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private FeedBackService feedBackService;
    @Autowired
    private FeedbackRepository feedbackRepository;

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

    @Test
    @DisplayName("피드백Id로 피드백 단건 조회 테스트")
    void feedbackSelectTestByFeedbackId() {
        //given
        FeedbackDTO testData = new FeedbackDTO();
        testData.setFeedbackId(1L);

        //when
        FeedbackDTO selectedData = feedbackMapper.findFeedbackByFeedbackId(1L);

        //then
        assertEquals(testData.getFeedbackId(), selectedData.getFeedbackId());
    }

    @Test
    @DisplayName("피드백 수정 성공 테스트")
    void updateFeedbackSuccessTest() {

    }

    @Test
    @DisplayName("피드백 수정 실패 - 평가 기간 외 수정 시도")
    void updateFeedbackFailureOutOfPeriodTest() {
        // given
        Long feedbackId = 1L;  // 실제 DB의 피드백 ID

        UpdateFeedbackRequestDTO updateRequest = new UpdateFeedbackRequestDTO();
        updateRequest.setContent("수정 시도 내용");

        // when & then
        assertThrows(CommonException.class, () -> {
            feedBackService.updateFeedback(feedbackId, updateRequest);
        }, "평가 기간이 아닌 경우 수정이 불가능해야 합니다.");
    }

    @Test
    @DisplayName("피드백 수정 실패 - 존재하지 않는 피드백")
    void updateFeedbackFailureNotFoundTest() {
        // given
        Long nonExistentFeedbackId = 999999L;

        UpdateFeedbackRequestDTO updateRequest = new UpdateFeedbackRequestDTO();
        updateRequest.setContent("수정 시도 내용");

        // when & then
        assertThrows(CommonException.class, () -> {
            feedBackService.updateFeedback(nonExistentFeedbackId, updateRequest);
        }, "존재하지 않는 피드백에 대한 수정 시도시 예외가 발생해야 합니다.");
    }

    @Test
    @DisplayName("피드백 수정 실패 - 평가 정책 없음")
    void updateFeedbackFailureNoPolicyTest() {

    }

}