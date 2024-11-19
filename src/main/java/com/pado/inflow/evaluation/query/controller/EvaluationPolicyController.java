package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.service.EvaluationPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryEvaluationPolicyController")
@RequestMapping("/api/evaluation/evaluationPolicy")
public class EvaluationPolicyController {

    @Autowired
    private final EvaluationPolicyService evaluationPolicyService;

    public EvaluationPolicyController(EvaluationPolicyService evaluationPolicyService) {
        this.evaluationPolicyService = evaluationPolicyService;
    }

    @GetMapping("/find")
    public ResponseDTO<?> findEvaluationPolicyByYearAndHalf(
            @RequestParam(value = "year") Integer year,
            @RequestParam(value = "half") String half
    ){
        List<EvaluationPolicyDTO> selectedEvaluationPolicy =
                evaluationPolicyService.findPolicyWithYearAndHalf(year,half);
        return ResponseDTO.ok(selectedEvaluationPolicy);
    }

    @GetMapping("/{evaluationPolicyId}")
    public ResponseDTO<?> findEvaluationPolicyByevaluationPolicyId(
            @PathVariable( value = "evaluationPolicyId") Long evaluationPolicyId
    ) {
        EvaluationPolicyDTO evaluationPolicy = evaluationPolicyService.findPolicyByevaluationPolicyId(evaluationPolicyId);
        return ResponseDTO.ok(evaluationPolicy);
    }
}
