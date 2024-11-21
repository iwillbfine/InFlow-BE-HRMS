package com.pado.inflow.statistics.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.statistics.query.dto.TaskEvaluationDTO;
import com.pado.inflow.statistics.query.service.TaskEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("TEQueryController")
@RequestMapping("/api/statistics/task-evaluation")
public class TaskEvaluationController {

    private final TaskEvaluationService taskEvaluationService;

    @Autowired
    public TaskEvaluationController(TaskEvaluationService taskEvaluationService) {
        this.taskEvaluationService = taskEvaluationService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm healthy";
    }

    // 전체 과제평가 통계 조회
    @GetMapping
    public ResponseDTO getTaskEvaluation() {
        List<TaskEvaluationDTO> result = taskEvaluationService.getTaskEvaluation(null);
        return ResponseDTO.ok(result);
    }

    // 특정 년도의 과제평가 통계 조회
    @GetMapping("/year/{year}")
    public ResponseDTO getYearlyEmployeeNum(@PathVariable("year") String year) {
        List<TaskEvaluationDTO> result = taskEvaluationService.getTaskEvaluation(year);
        return ResponseDTO.ok(result);
    }

    // 특정 부서의 과제평가 통계 조회
    @GetMapping("/department/{deptCode}")
    public ResponseDTO getDeptEmployeeNum(@PathVariable("deptCode") String deptCode) {
        List<TaskEvaluationDTO> result = taskEvaluationService.getDeptYearEmpNums(deptCode);
        return ResponseDTO.ok(result);
    }
}