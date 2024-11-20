package com.pado.inflow.evaluation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.command.application.service.TaskTypeService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateTaskTypeResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateTaskTypeResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("CommandTaskTypeController")
@RequestMapping("/api/evaluations/taskType")        // 리소스 나타낼 때 복수형 사용하기
public class TaskTypeController {

    @Autowired
    private final TaskTypeService taskTypeService;

    public TaskTypeController(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }

    // 과제 유형 생성
    @PostMapping("/create")
    public ResponseDTO<CreateTaskTypeResponseDTO> createTaskTypeByTaskTypeName(
            @RequestBody CreateTaskTypeRequestDTO CreateTaskTypeRequestDTO
            ) {
        CreateTaskTypeResponseDTO createdTaskType =
                taskTypeService.createTaskTypeByTaskTypeName(CreateTaskTypeRequestDTO);
        return ResponseDTO.ok(createdTaskType);
    }

    // 과제 유형 수정
    @PatchMapping("/{taskTypeId}")
    public ResponseDTO<UpdateTaskTypeResponseDTO> updateTaskTypeByTaskTypeId(
            @PathVariable( value = "taskTypeId") Long taskTypeId
           ,@RequestBody UpdateTaskTypeRequestDTO updateTaskTypeRequestDTO
    ) {
        UpdateTaskTypeResponseDTO updatedTakType =
                taskTypeService.updateTaskTypeByTaskTypeId(taskTypeId, updateTaskTypeRequestDTO);
        return ResponseDTO.ok(updatedTakType);
    }

    // 과제 유형 삭제
    @DeleteMapping("/{taskTypeId}")
    public ResponseDTO<String> deleteTaskTypeByTaskTypeId(
            @PathVariable( value = "taskTypeId") Long taskTypeId
    ) {
        taskTypeService.deleteTaskTypeByTaskTypeId(taskTypeId);
        return ResponseDTO.ok("과제 유형 삭제가 완료되었습니다.");
    }


}
