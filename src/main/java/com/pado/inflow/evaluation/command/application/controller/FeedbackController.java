package com.pado.inflow.evaluation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.command.application.service.FeedBackService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateEvaluationPolicyResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateFeedbackResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("CommandFeedbackController")
@RequestMapping("/api/evaluations/feedback")
public class FeedbackController {

    @Autowired
    private final FeedBackService feedBackService;

    public FeedbackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }


    @PostMapping("/feedbackCreation")
    public ResponseDTO<CreateFeedbackResponseDTO> feedbackCreation(
            @RequestBody CreateFeedbackRequestDTO createFeedbackRequestDTO
            ) {
        CreateFeedbackResponseDTO createFeedbackResponseDTO = feedBackService.createFeedback(createFeedbackRequestDTO);
        return ResponseDTO.ok(createFeedbackResponseDTO);
    }
}
