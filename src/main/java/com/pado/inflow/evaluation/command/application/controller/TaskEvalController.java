package com.pado.inflow.evaluation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.command.application.service.TaskEvalService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskEvalResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController("CommandTaskEvalController")
@RequestMapping("/api/evaluations/taskEval")
public class TaskEvalController {

    private final TaskEvalService taskEvalService;


    public TaskEvalController(TaskEvalService taskEvalService) {
        this.taskEvalService = taskEvalService;
    }

    // 평가항목별 평가 생성 - rel_eval_status 로직 수정 필요.
    @PostMapping("/taskEvalCreation")
    public ResponseDTO<TaskEvalResponseDTO> createTaskEval(
            @RequestBody CreateTaskEvalRequestDTO createTaskEvalRequestDTO
           ,@RequestParam( value ="year") Integer year
           ,@RequestParam( value ="half") String half
           ,@RequestParam( value ="employeeId") Long employeeId
    ) {
        TaskEvalResponseDTO createdTaskEval =
                taskEvalService.createTaskEval(createTaskEvalRequestDTO, year, half, employeeId);
        return ResponseDTO.ok(createdTaskEval);
    }

    // 과제별 평가 수정
    @PatchMapping("/{taskEvalId}")
    public ResponseDTO<TaskEvalResponseDTO> updateTaskEval(
            @PathVariable(value = "taskEvalId") Long taskEvalId
           ,@RequestBody UpdateTaskEvalRequestDTO updateTaskEvalRequestDTO
    ) {
        TaskEvalResponseDTO updatedTaskEval = taskEvalService.updateTaskEval(taskEvalId, updateTaskEvalRequestDTO);
        return ResponseDTO.ok(updatedTaskEval);
    }

}
