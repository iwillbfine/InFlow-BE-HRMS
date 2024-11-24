package com.pado.inflow.evaluation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.command.application.service.TaskItemService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskItemRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskItemResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController("CommandTaskItemController")
@RequestMapping("/api/evaluations/taskItem")
public class TaskItemController {

    private final TaskItemService taskItemService;

    public TaskItemController(TaskItemService taskItemService) {
        this.taskItemService = taskItemService;
    }

    @PostMapping("/taskItemCreation")
    public ResponseDTO<TaskItemResponseDTO> taskItemCreation(
            @RequestParam( value = "year") int year
           ,@RequestParam( value = "half") String half
           ,@RequestParam( value = "taskTypeId") Long taskTypeId
           ,@RequestBody CreateTaskItemRequestDTO createTaskItemRequestDTO
            ) {
        TaskItemResponseDTO createdTaskItem =
                taskItemService.createTaskItem(year, half, taskTypeId, createTaskItemRequestDTO);
        return ResponseDTO.ok(createdTaskItem);

    }
}
