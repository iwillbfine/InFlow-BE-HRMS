package com.pado.inflow.employee.attach.query.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
        assertTrue(languageTestService.getLanguageTestsAll("1").size() > 0);
    }

}