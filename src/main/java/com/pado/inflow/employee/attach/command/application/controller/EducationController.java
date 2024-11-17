package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.command.application.service.EducationService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.EducationDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ECommandController")
@RequestMapping("/api/employee/education")
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    // 사원의 학력정보 등록
    @PostMapping
    public ResponseDTO addEdu(@RequestBody List<EducationDTO> educations) {
        List<Education> result = educationService.addEdus(educations);
        return ResponseDTO.ok(result);
    }

    // 사원의 학력정보 수정
    @PutMapping
    public ResponseDTO modifyEdu(@RequestBody List<EducationDTO> educations) {
        List<Education> result = educationService.modifyEdus(educations);
        return ResponseDTO.ok(result);
    }

    // 사원의 학력정보 삭제
    @DeleteMapping
    public ResponseDTO deleteEdu(@RequestBody List<Long> educations) {
        Boolean result = educationService.deleteEdus(educations);
        return ResponseDTO.ok("삭제 완료");
    }
}
