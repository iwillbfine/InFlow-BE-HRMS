package com.pado.inflow.evaluation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.command.application.service.FeedBackService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateEvaluationPolicyResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateFeedbackResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateFeedbackResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("CommandFeedbackController")
@RequestMapping("/api/evaluations/feedback")
@Slf4j
public class FeedbackController {

    @Autowired
    private final FeedBackService feedBackService;

    public FeedbackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }


    // 피드백 생성
    @PostMapping("/feedbackCreation")
    public ResponseDTO<CreateFeedbackResponseDTO> feedbackCreation(
            @RequestBody CreateFeedbackRequestDTO createFeedbackRequestDTO
            ) {
        log.info("createFeedbackRequestDTO: {}", createFeedbackRequestDTO);
        CreateFeedbackResponseDTO createFeedbackResponseDTO = feedBackService.createFeedback(createFeedbackRequestDTO);
        return ResponseDTO.ok(createFeedbackResponseDTO);
    }

    // 피드백 수정
    @PatchMapping("/{feedbackId}")
    public ResponseDTO<UpdateFeedbackResponseDTO> feedbackUpdate(
            @PathVariable(value = "feedbackId") Long feedbackId
            , @RequestBody UpdateFeedbackRequestDTO updateFeedbackRequestDTO
    ) {
        UpdateFeedbackResponseDTO updateFeedbackResponseDTO = feedBackService.updateFeedback(feedbackId, updateFeedbackRequestDTO);
        return ResponseDTO.ok(updateFeedbackResponseDTO);
    }

    // 피드백 삭제 ( ITO )

}
