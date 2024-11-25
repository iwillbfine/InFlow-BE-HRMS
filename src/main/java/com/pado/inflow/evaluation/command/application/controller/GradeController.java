package com.pado.inflow.evaluation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.evaluation.command.application.service.GradeService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateGradeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.GradeResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("CommandGradeController")
@RequestMapping("/api/evaluations/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // 등급 생성 ( 현 상황에서는 등급을 순서대로 생성해야함 )
    @PostMapping("/gradeCreation")
    public ResponseDTO<List<GradeResponseDTO>> GradeCreate(
            @RequestBody CreateGradeRequestDTO createGradeRequestDTO
           ,@RequestParam(value = "year") Integer year
           ,@RequestParam(value = "half") String half
    ) {
        List<GradeResponseDTO> createdGrades =
                gradeService.createGrade(createGradeRequestDTO, year, half);
        return ResponseDTO.ok(createdGrades);
    }

    // 등급 수정
    @PatchMapping("/{gradeId}")
    public ResponseDTO<GradeResponseDTO> GradeUpdate(
            @PathVariable(value = "gradeId") Long gradeId
    ) {
        GradeResponseDTO updatedGrade = gradeService.updateGrade(gradeId);
        return ResponseDTO.ok(updatedGrade);
    }

}
