package com.pado.inflow.employee.attach.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.attach.command.application.service.LanguageTestService;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.LanguageTestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("LTCommandController")
@RequestMapping("/api/employees/language-tests")
public class LanguageTestController {

    private final LanguageTestService languageTestService;

    @Autowired
    public LanguageTestController(LanguageTestService languageTestService) {
        this.languageTestService = languageTestService;
    }

    // 사원의 어학 정보 등록
    @PostMapping
    public ResponseDTO<List<LanguageTestDTO>> addLanguageTest(@RequestBody List<LanguageTestDTO> langTests) {
        List<LanguageTestDTO> result = languageTestService.addLangTests(langTests);
        return ResponseDTO.ok(result);
    }

    // 사원의 어학 정보 수정
    @PutMapping
    public ResponseDTO<List<LanguageTestDTO>> modifyLanguageTest(@RequestBody List<LanguageTestDTO> langTests) {
        List<LanguageTestDTO> result = languageTestService.modifyLangTests(langTests);
        return ResponseDTO.ok(result);
    }

    // 사원의 어학 정보 삭제
    @DeleteMapping
    public ResponseDTO<String> deleteLanguageTest(@RequestBody List<Long> langTests) {
        languageTestService.deleteLangTests(langTests);
        return ResponseDTO.ok("어학 정보 삭제 완료");
    }
}
