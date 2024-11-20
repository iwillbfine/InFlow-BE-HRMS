package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateEvaluationPolicyResponseDTO;

public interface EvaluationPolicyService {
    CreateEvaluationPolicyResponseDTO createEvaluationPolicy(CreateEvaluationPolicyRequestDTO createEvaluationPolicyRequestDTO);
}
