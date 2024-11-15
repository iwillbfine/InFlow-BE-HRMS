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
    public ResponseDTO addQualification(@RequestBody List<QualificationDTO> qualifications) {
        List<Qualification> result = qualificationService.addQualifications(qualifications);
        return ResponseDTO.ok(result);
    }

    // 사원의 자격증 정보 수정
    @PostMapping("/modify")
    public ResponseDTO modifyQualification(@RequestBody List<QualificationDTO> qualifications) {
        List<Qualification> result = qualificationService.modifyQualifications(qualifications);
        return ResponseDTO.ok(result);
    }

    // 사원의 자격증 정보 삭제
    @DeleteMapping("/delete")
    public ResponseDTO deleteQualification(@RequestBody List<Long> qualifications) {
        Boolean result = qualificationService.deleteQualifications(qualifications);
        return ResponseDTO.ok("자격증 삭제 완료");
    }
}
