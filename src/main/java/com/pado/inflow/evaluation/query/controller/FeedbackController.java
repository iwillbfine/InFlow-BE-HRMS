package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.FeedbackDTO;
import com.pado.inflow.evaluation.query.service.FeedbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryFeedbackController")
@RequestMapping("/api/evaluation/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/find")
    public ResponseDTO<?> findFeedback(
            @RequestParam( value = "empId") Long empId
           ,@RequestParam( value =  "year") Integer year
           ,@RequestParam( value =  "half") String half
    ) {
        FeedbackDTO selectedFeedback =
                feedbackService.findFeedbackByempIdAndYearAndHalf(empId, year, half);
        return ResponseDTO.ok(selectedFeedback);
    }
}
