package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.query.dto.EducationDTO;
import com.pado.inflow.employee.attach.query.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("EQueryController")
@RequestMapping("/api/employees/educations")
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm OK!!";
    }

    // 전 사원의 학력 조회
    @GetMapping
    public ResponseDTO<List<EducationDTO>> getEducationAll() {
        List<EducationDTO> result = educationService.getEduAll(null);
        return ResponseDTO.ok(result);
    }

    // 사원 한 명의 학력 조회
    @GetMapping("/{employeeId}")
    public ResponseDTO<List<EducationDTO>> getEducationOne(@PathVariable("employeeId") String employeeId) {
        List<EducationDTO> result = educationService.getEduAll(employeeId);
        return ResponseDTO.ok(result);
    }
}
