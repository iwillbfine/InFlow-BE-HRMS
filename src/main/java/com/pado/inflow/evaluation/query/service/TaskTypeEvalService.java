package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.TaskTypeEvalDTO;

import java.util.List;

public interface TaskTypeEvalService {
    List<TaskTypeEvalDTO> findByEvaluationId(Long evaluationId);
}
