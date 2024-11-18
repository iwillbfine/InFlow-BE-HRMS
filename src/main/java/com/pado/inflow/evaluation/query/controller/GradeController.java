package com.pado.inflow.evaluation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.query.dto.GradeDTO;
import com.pado.inflow.evaluation.query.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryGradeController")
@RequestMapping("/api/evaluation/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // 평가등급 리스트 조회  - Unique 다시 건다면 테스트 코드도 수정해야함을 알고있자 !
    @GetMapping("/find")
    public ResponseDTO<?> findGradeByYearAndHalf(
            @RequestParam( value = "year") Integer year
           ,@RequestParam( value = "half") String half
    ) {
        List<GradeDTO> gradeList =
                gradeService.findGradeByYearAndHalf(year, half);

        return ResponseDTO.ok(gradeList);

    }

    // 평가등급 단건 조회
    @GetMapping("/{gradeId}")
    public ResponseDTO<?> findGradeByGradeId(
            @PathVariable( value = "gradeId") Long gradeId
    ) {
        GradeDTO grade = gradeService.findGradeByGradeId(gradeId);
        return ResponseDTO.ok(grade);
    }
}
