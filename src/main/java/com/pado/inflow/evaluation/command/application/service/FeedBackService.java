package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateFeedbackRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateFeedbackResponseDTO;

public interface FeedBackService {
    CreateFeedbackResponseDTO createFeedback(CreateFeedbackRequestDTO createFeedbackRequestDTO);
}
