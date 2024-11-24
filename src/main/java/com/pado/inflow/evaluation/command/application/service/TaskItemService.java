package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskItemRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskItemReqeustDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskItemResponseDTO;

public interface TaskItemService {
    TaskItemResponseDTO createTaskItem(int year, String half, Long taskTypeId, CreateTaskItemRequestDTO createTaskItemRequestDTO);

    TaskItemResponseDTO UpdateTaskItem(Long taskItemId, UpdateTaskItemReqeustDTO taskItemUpdateRequestDTO);
}
