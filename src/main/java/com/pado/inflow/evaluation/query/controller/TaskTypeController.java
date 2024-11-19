package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.TaskTypeDTO;
import com.pado.inflow.evaluation.query.service.TaskTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryTaskTypeController")
@RequestMapping("/api/evaluations/taskType")
public class TaskTypeController {


    private final TaskTypeService taskTypeService;

    public TaskTypeController(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }

    // 과제 유형 조회
    @GetMapping("/allTaskType")
    public ResponseDTO<?> findTaskType() {
        List<TaskTypeDTO> taskTypes = taskTypeService.findAllTaskTypes();
        return ResponseDTO.ok(taskTypes);
    }
}
