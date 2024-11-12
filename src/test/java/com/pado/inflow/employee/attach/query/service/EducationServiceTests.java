package com.pado.inflow.employee.attach.query.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EducationServiceTests {

    private final EducationService educationService;

    @Autowired
    public EducationServiceTests(EducationService educationService) {
        this.educationService = educationService;
    }

    @DisplayName("전 사원의 학력 조회")
    @Test
    public void getAllEduTests() {
        assertTrue(educationService.getEduAll().size() > 0);
    }

    @DisplayName("사원 한 명의 학력 조회")
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    public void getAllEduTests(Long id) {
        assertTrue(educationService.getEduOne(id).size() > 0);
    }
}