package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.GradeDTO;
import com.pado.inflow.evaluation.query.service.GradeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryGradePolicyController")
@RequestMapping("/api/evaluation/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // 등급 조회
    @GetMapping("/find")
    public ResponseDTO<?> findGradeByYearAndHalf(
            @RequestParam( value = "year") Integer year
           ,@RequestParam( value = "half") String half
    ) {
        List<GradeDTO> gradeList = gradeService.findGradeByYearAndHalf(year, half);

        return ResponseDTO.ok(gradeList);

    }
}
