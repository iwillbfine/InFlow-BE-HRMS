package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.FeedbackDTO;
import com.pado.inflow.evaluation.query.service.FeedbackService;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

@RestController("queryFeedbackController")
@RequestMapping("/api/evaluations/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/find")
    public ResponseDTO<?> findFeedbacks(
            @RequestParam( value = "empId") Long empId
           ,@RequestParam( value =  "year") Integer year
           ,@RequestParam( value =  "half") String half
    ) {
        FeedbackDTO selectedFeedback =
                feedbackService.findFeedbackByempIdAndYearAndHalf(empId, year, half);
        return ResponseDTO.ok(selectedFeedback);
    }

    // 피드백Id로 단건 조회
    @GetMapping("/{feedbackId}")
    public ResponseDTO<?> findFeedback(
            @PathVariable( value = "feedbackId") Long feedbackId
    ) {
        FeedbackDTO selectedFeedback = feedbackService.findFeedbackByFeedbackId(feedbackId);
        return ResponseDTO.ok(selectedFeedback);
    }
}
