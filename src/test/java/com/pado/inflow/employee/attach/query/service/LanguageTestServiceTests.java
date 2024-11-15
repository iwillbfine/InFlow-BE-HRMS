package com.pado.inflow.employee.attach.query.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LanguageTestServiceTests {

    private final LanguageTestService languageTestService;

    @Autowired
    public LanguageTestServiceTests(LanguageTestService languageTestService) {
        this.languageTestService = languageTestService;
    }

    @DisplayName("전 사원의 어학시험 정보 조회")
    @Test
    public void getLanguageTests() {
        assertTrue(languageTestService.getLanguageTestsAll().size() > 0);
    }

    @DisplayName("사원 한 명의 어학시험 정보 조회")
    @ValueSource(longs = {1L, 2L})
    public void getLanguageTest(Long employeeId) {
        assertTrue(languageTestService.getLanguageTestsOne(employeeId).size() > 0);
    }
}