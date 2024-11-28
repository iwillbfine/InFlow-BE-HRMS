package com.pado.inflow.employee.attach.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.query.dto.LanguageTestDTO;
import com.pado.inflow.employee.attach.query.service.LanguageTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController("LTQueryController")
@RequestMapping("/api/employees/language-tests")
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

    // 전 사원의 어학 정보 조회
    @GetMapping
    public ResponseDTO<List<LanguageTestDTO>> getLanguageTestAll() {
        List<LanguageTestDTO> result = languageTestService.getLanguageTestsAll(null);
        return ResponseDTO.ok(result);
    }

    // 사원 한 명의 어학 정보 조회
    @GetMapping("/{employeeId}")
    public ResponseDTO<List<LanguageTestDTO>> getLanguageTestOne(@PathVariable("employeeId") String employeeId) {
        List<LanguageTestDTO> result = languageTestService.getLanguageTestsAll(employeeId);
        return ResponseDTO.ok(result);
    }

    // 언어 코드 정보 조회
    @GetMapping("/languages")
    public ResponseDTO<List<HashMap<String, String>>> getLanguageCode() {
        List<HashMap<String, String>> result = languageTestService.getLanguageCode();
        return ResponseDTO.ok(result);
    }
}
