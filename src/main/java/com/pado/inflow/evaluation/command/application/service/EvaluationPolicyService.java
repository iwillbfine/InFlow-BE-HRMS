package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateEvaluationPolicyRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateEvaluationPolicyResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateEvaluationPolicyResponseDTO;

public interface EvaluationPolicyService {
    CreateEvaluationPolicyResponseDTO createEvaluationPolicy(CreateEvaluationPolicyRequestDTO createEvaluationPolicyRequestDTO);

    UpdateEvaluationPolicyResponseDTO updateEvaluationPolicy(UpdateEvaluationPolicyRequestDTO updateEvaluationPolicyRequestDTO, Long evaluationPolicyId);

    void deleteEvaluationPolicyByEvaluationPolicyId(Long evaluationPolicyId);
}
