package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.employee.attach.query.dto.EducationDTO;
import com.pado.inflow.employee.attach.query.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("EQueryController")
@RequestMapping("/api/employee/educations")
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("health")
    public String healthCheck() {
        return "I'm OK!!";
    }

    // 전 사원의 학력 조회
    @GetMapping("/")
    public ResponseEntity getEducationAll() {
        try {
            List<EducationDTO> result = educationService.getEduAll();
            return result.size()>0 ? ResponseEntity.ok(result) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("조회 결과 없음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("학력 조회 실패");
        }
    }

    // 사원 한 명의 학력 조회
    @GetMapping("/{employeeId}")
    public ResponseEntity getEducationOne(@PathVariable("employeeId") Long employeeId) {
        try {
            List<EducationDTO> result = educationService.getEduOne(employeeId);
            return result.size()>0 ? ResponseEntity.ok(result) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("조회 결과 없음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("학력 조회 실패");
        }
    }


}
