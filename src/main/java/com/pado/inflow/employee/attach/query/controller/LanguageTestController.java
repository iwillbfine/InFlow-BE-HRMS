package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.query.dto.LanguageTestDTO;
import com.pado.inflow.employee.attach.query.service.LanguageTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("LTQueryController")
@RequestMapping("/api/employee/language-test")
public class LanguageTestController {

    private final LanguageTestService languageTestService;

    @Autowired
    public LanguageTestController(LanguageTestService languageTestService) {
        this.languageTestService = languageTestService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm OK!!";
    }

    // 전 사원의 경력 조회
    @GetMapping("/")
    public ResponseDTO getLanguageTestAll() {
        List<LanguageTestDTO> result = languageTestService.getLanguageTestsAll();
        return ResponseDTO.ok(result);
    }

    // 사원 한 명의 경력 조회
    @GetMapping("/{employeeId}")
    public ResponseDTO getLanguageTestOne(@PathVariable("employeeId") Long employeeId) {
        List<LanguageTestDTO> result = languageTestService.getLanguageTestsOne(employeeId);
        return ResponseDTO.ok(result);
    }
}
