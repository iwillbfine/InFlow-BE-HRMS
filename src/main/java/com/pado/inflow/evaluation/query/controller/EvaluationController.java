package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.EvaluationDTO;
import com.pado.inflow.evaluation.query.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryEvaluationController")
@RequestMapping("/api/evaluations/evaluation")
public class EvaluationController {

    @Autowired
    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // 최종 평가 등급 조회
    @GetMapping("/leader")
    public ResponseDTO<?> findFinGradeByEmpIdAndYearAndHalf(
            @RequestParam(value = "empId") Long empId
           ,@RequestParam(value = "year") Integer year
           ,@RequestParam(value = "half") String half
    ) {
        EvaluationDTO selectedEvaluation =
                evaluationService.findEvaluationGrade(empId, year, half);
        return ResponseDTO.ok(selectedEvaluation);
    }

    // 자기 평가 조회
    @GetMapping("/mine")
    public ResponseDTO<?> findEvaluationByEmpIdAndYearAndHalf(
            @RequestParam(value = "empId") Long empId
           ,@RequestParam(value = "year") Integer year
           ,@RequestParam(value = "half") String half
    ) {

        EvaluationDTO selectedEvaluation =
                evaluationService.findEvaluation(empId, year, half);
        return ResponseDTO.ok(selectedEvaluation);
    }

    // 평가 리스트 조회
    @GetMapping("/list")
    public ResponseDTO<?> findEvaluations(
            @RequestParam(value = "empId") Long empId
           ,@RequestParam(value = "year") Integer year
           ,@RequestParam(value = "half") String half
    ) {
        List<EvaluationDTO> selectedEvaluations = evaluationService.findEvaluations(empId, year, half);
        return ResponseDTO.ok(selectedEvaluations);
    }


    // 평가 ID로 평가 단건 조회
    @GetMapping("/{evaluationId}")
    public ResponseDTO<?> findEvaluationByEvaluationId(
            @PathVariable( value = "evaluationID") Long evaluationId
    ) {
        EvaluationDTO selectedEvaluation =
                evaluationService.findEvaluationByEvaluationId(evaluationId);
        return ResponseDTO.ok(selectedEvaluation);

    }



}
