package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.query.dto.QualificationDTO;
import com.pado.inflow.employee.attach.query.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("QQueryController")
@RequestMapping("/api/employee/qualification")
public class QualificationController {

    private final QualificationService qualificationService;

    @Autowired
    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm OK!!";
    }

    // 전 사원의 자격증 조회
    @GetMapping("/")
    public ResponseDTO<List<QualificationDTO>> getQualificationAll() {
        List<QualificationDTO> result = qualificationService.getQualificationAll();
        return ResponseDTO.ok(result);
    }

    // 사원 한 명의 자격증 조회
    @GetMapping("/{employeeId}")
    public ResponseDTO<List<QualificationDTO>> getQualificationOne(@PathVariable("employeeId") Long employeeId) {
        List<QualificationDTO> result = qualificationService.getQualificationOne(employeeId);
        return ResponseDTO.ok(result);
    }
}
