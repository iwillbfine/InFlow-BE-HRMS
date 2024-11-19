package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;

import java.util.List;

public interface EvaluationPolicyService {

    List<EvaluationPolicyDTO> findPolicyWithYearAndHalf(Integer year, String half);

    EvaluationPolicyDTO findPolicyByevaluationPolicyId(Long evaluationPolicyId);
}
