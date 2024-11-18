package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryEvaluationController")
@RequestMapping("/api/evaluation/evaluation")
public class EvaluationController {

    @Autowired
    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // 최종 평가 등급 조회
    @GetMapping("/")
    public ResponseDTO<?> findFinGradeByEmpIdAndYearAndHalf(
            @RequestParam(value = "empId") Long empId
           ,@RequestParam(value = "year") Integer year
           ,@RequestParam(value = "half") String half
    ) {
        EvaluationDTO selectedEvaluation =
                evaluationService.findEvaluationGrade(empId, year, half);
        return ResponseDTO.ok(selectedEvaluation);
    }

}
