package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateTaskTypeResponseDTO;

public interface TaskTypeService {
    CreateTaskTypeResponseDTO createTaskTypeByTaskTypeName(CreateTaskTypeRequestDTO createTaskTypeRequestDTO);
}
