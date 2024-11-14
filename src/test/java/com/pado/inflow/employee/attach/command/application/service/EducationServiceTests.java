package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.EducationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EducationServiceTests {

    private final EducationService educationService;

    @Autowired
    public EducationServiceTests(EducationService educationService) {
        this.educationService = educationService;
    }

    @DisplayName("사원의 학력정보 등록")
    @Test
    public void addEducation() {
        List<EducationDTO> list = new ArrayList<>();
        list.add(new EducationDTO("강남초등학교",
                LocalDate.of(2000, 3, 2),
                LocalDate.of(2006, 2, 15),
                "초등학교", null, 1L));
        assertTrue(educationService.addEdus(list) != null);
    }
}