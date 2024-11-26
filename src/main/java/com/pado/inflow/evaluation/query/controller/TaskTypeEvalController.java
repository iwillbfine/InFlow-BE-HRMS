package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.TaskTypeEvalDTO;
import com.pado.inflow.evaluation.query.service.TaskTypeEvalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryTaskTypeEvalController")
@RequestMapping("/api/evaluations/taskTypeEval")
public class TaskTypeEvalController {

    private final TaskTypeEvalService taskTypeEvalService;

    public TaskTypeEvalController(TaskTypeEvalService taskTypeEvalService) {
        this.taskTypeEvalService = taskTypeEvalService;
    }


    // 평가 ID로 TaskTypeEval 리스트 조회
    @GetMapping("/{evaluationId}")
    public ResponseDTO<?> findAllTaskTypeEvalByEvaluationId(
            @PathVariable(value = "evaluationId") Long evaluationId
    ) {
        List<TaskTypeEvalDTO> selectedTaskTypeEvals = taskTypeEvalService.findByEvaluationId(evaluationId);
        return ResponseDTO.ok(selectedTaskTypeEvals);
    }
}
