package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.command.application.service.QualificationService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.QualificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("QCommandController")
@RequestMapping("/api/employees/qualifications")
public class QualificationController {

    private final QualificationService qualificationService;

    @Autowired
    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    // 사원의 자격증 정보 등록
    @PostMapping
    public ResponseDTO<List<QualificationDTO>> addQualification(@RequestBody List<QualificationDTO> qualifications) {
        List<QualificationDTO> result = qualificationService.addQualifications(qualifications);
        return ResponseDTO.ok(result);
    }

    // 사원의 자격증 정보 수정
    @PutMapping
    public ResponseDTO<List<QualificationDTO>> modifyQualification(@RequestBody List<QualificationDTO> qualifications) {
        List<QualificationDTO> result = qualificationService.modifyQualifications(qualifications);
        return ResponseDTO.ok(result);
    }

    // 사원의 자격증 정보 삭제
    @DeleteMapping
    public ResponseDTO<String> deleteQualification(@RequestBody List<Long> qualifications) {
        Boolean result = qualificationService.deleteQualifications(qualifications);
        return ResponseDTO.ok("자격증 삭제 완료");
    }
}
