package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskEvalResponseDTO;

public interface TaskEvalService {

    TaskEvalResponseDTO createTaskEval(CreateTaskEvalRequestDTO createTaskEvalRequestDTO, Integer year, String half, Long employeeId);

    TaskEvalResponseDTO updateTaskEval(Long taskEvalId, UpdateTaskEvalRequestDTO updateTaskEvalRequestDTO);
}
