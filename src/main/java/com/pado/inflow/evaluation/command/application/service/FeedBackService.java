package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateFeedbackResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateFeedbackResponseDTO;

public interface FeedBackService {
    CreateFeedbackResponseDTO createFeedback(CreateFeedbackRequestDTO createFeedbackRequestDTO);

    UpdateFeedbackResponseDTO updateFeedback(Long feedbackId, UpdateFeedbackRequestDTO updateFeedbackRequestDTO);
}
