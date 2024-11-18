package com.pado.inflow.evaluation.query.controller;


import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;
import com.pado.inflow.evaluation.query.service.TaskEvalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryTaskEvalController")
@RequestMapping("/api/evaluation/taskEval")
public class TaskEvalController {

    private final TaskEvalService taskEvalService;

    public TaskEvalController(TaskEvalService taskEvalService) {
        this.taskEvalService = taskEvalService;
    }

    // 과제별 평가 조회
    @GetMapping("/find")
    public ResponseDTO<?> findTaskEvalByEmpIdAndYearAndHalf(
            @RequestParam( value = "empId") Long empId
           ,@RequestParam( value = "year")  Integer year
           ,@RequestParam( value = "half")  String half
    ) {
        List<TaskEvalDTO> taskEvalList = taskEvalService.findTaskEval(empId, year, half);

        return ResponseDTO.ok(taskEvalList);
    }
}
