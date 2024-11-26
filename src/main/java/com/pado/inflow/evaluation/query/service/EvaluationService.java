package com.pado.inflow.evaluation.query.service;


import com.pado.inflow.evaluation.query.dto.EvaluationDTO;

import java.util.List;

public interface EvaluationService {
    EvaluationDTO findEvaluationGrade(Long empId, Integer year, String half);

    EvaluationDTO findEvaluationByEvaluationId(Long evaluationId);

    EvaluationDTO findEvaluation(Long empId, Integer year, String half);

    List<EvaluationDTO> findEvaluations(Long empId, Integer year, String half);
}
