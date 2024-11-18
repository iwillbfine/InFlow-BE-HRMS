package com.pado.inflow.evaluation.query.service;


import com.pado.inflow.evaluation.query.dto.EvaluationDTO;

public interface EvaluationService {
    EvaluationDTO findEvaluationGrade(Long empId, Integer year, String half);
}
