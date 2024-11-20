package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateTaskTypeResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateTaskTypeResponseDTO;

public interface TaskTypeService {
    CreateTaskTypeResponseDTO createTaskTypeByTaskTypeName(CreateTaskTypeRequestDTO createTaskTypeRequestDTO);

    UpdateTaskTypeResponseDTO updateTaskTypeByTaskTypeId(Long taskTypeId, UpdateTaskTypeRequestDTO updateTaskTypeRequestDTO);

    void deleteTaskTypeByTaskTypeId(Long taskTypeId);
}
