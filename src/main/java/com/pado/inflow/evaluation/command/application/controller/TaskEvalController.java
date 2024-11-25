package com.pado.inflow.evaluation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.command.application.service.TaskEvalService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskEvalRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskEvalResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskEvalEntity;
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

}
