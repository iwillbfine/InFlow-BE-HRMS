package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;

import java.util.List;

public interface TaskEvalService {
    List<TaskEvalDTO> findTaskEval(Long empId, Integer year, String half);


    TaskEvalDTO getTaskEvalItem(Long taskEvalId);

    List<TaskEvalDTO> findTaskEvalsByEvaluationId(Long evaluationId);
}
