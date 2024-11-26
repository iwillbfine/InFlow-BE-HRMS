package com.pado.inflow.evaluation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.command.application.service.TaskTypeEvalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("CommandTaskTypeEvalController")
@RequestMapping("/api/evaluations/taskTypeEval")
public class TaskTypeEvalController {

    private final TaskTypeEvalService taskTypeEvalService;

    public TaskTypeEvalController(TaskTypeEvalService taskTypeEvalService) {
        this.taskTypeEvalService = taskTypeEvalService;
    }

    @PatchMapping("/AllUsersScoreCalculation")
    public ResponseDTO<String> updateTaskTypeEvalAndEvaluation(
            @RequestParam( value = "year") Integer year
           ,@RequestParam( value = "half") String half
    ) {
        taskTypeEvalService.updateAllUsersTaskTypeEvalAndEvaluationScoreAndGrade(year, half);
        return ResponseDTO.ok("모든 사원에 대한 평가 점수 및 등급 반영이 완료되었습니다.");
    }
}