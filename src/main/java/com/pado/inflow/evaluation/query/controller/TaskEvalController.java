package com.pado.inflow.evaluation.query.controller;


import com.amazonaws.Request;
import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;
import com.pado.inflow.evaluation.query.service.TaskEvalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryTaskEvalController")
@RequestMapping("/api/evaluations/taskEval")
public class TaskEvalController {

    private final TaskEvalService taskEvalService;

    public TaskEvalController(TaskEvalService taskEvalService) {
        this.taskEvalService = taskEvalService;
    }

    // 과제별 평가 리스트 조회
    @GetMapping("/find")
    public ResponseDTO<?> findTaskEvalByEmpIdAndYearAndHalf(
            @RequestParam(value = "empId") Long empId
            , @RequestParam(value = "year") Integer year
            , @RequestParam(value = "half") String half
    ) {
        List<TaskEvalDTO> taskEvalList = taskEvalService.findTaskEval(empId, year, half);

        return ResponseDTO.ok(taskEvalList);
    }

    // 사원별 과제 평가 내역 단건 조회
    @GetMapping("/{taskEvalId}")
    public ResponseDTO<?> findTaskEvalByEmpIdAndTaskItemId(
            @PathVariable(value = "taskEvalId") Long taskEvalId
    ) {
        TaskEvalDTO taskEvalItem = taskEvalService.getTaskEvalItem(taskEvalId);
        return ResponseDTO.ok(taskEvalItem);
    }

    // 평가ID로 과제별 평가 List 조회
    @GetMapping("/byEvaluationId/{evaluationId}")
    public ResponseDTO<?> findTaskEvalByEvaluationId(
            @PathVariable(value = "evaluationId") Long evaluationId
    ) {
        List<TaskEvalDTO> selectedTaskEvalList = taskEvalService.findTaskEvalsByEvaluationId(evaluationId);
        return ResponseDTO.ok(selectedTaskEvalList);
    }

    // 자기평가 과제 리스트 조회
    @GetMapping("/individualTaskList")
    public ResponseDTO<?> findIndividualTaskListByEvaluationId(
            @RequestParam( value= "year") Integer year
           ,@RequestParam( value = "half") String half
           ,@RequestParam( value = "empId") Long empId
    ) {
        List<TaskEvalDTO> selectedIndividualTasks = taskEvalService.findIndividualTasks(year, half, empId);
        return ResponseDTO.ok(selectedIndividualTasks);

    }
}
