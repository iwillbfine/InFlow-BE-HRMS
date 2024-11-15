package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.command.application.service.QualificationService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.QualificationDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Qualification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("QCommandController")
@RequestMapping("/api/employee/qualification")
public class QualificationController {

    private final QualificationService qualificationService;

    @Autowired
    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    // 사원의 자격증 정보 등록
    @PostMapping("/add")
    public ResponseDTO addCareer(@RequestBody List<QualificationDTO> qualifications) {
        List<Qualification> result = qualificationService.addQualifications(qualifications);
        return ResponseDTO.ok(result);
    }
}
