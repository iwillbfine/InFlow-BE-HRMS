package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.QualificationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QualificationServiceTests {

    private final QualificationService qualificationService;

    @Autowired
    public QualificationServiceTests(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @DisplayName("사원의 자격증 정보 등록")
    @Test
    public void addQualification() {
        List<QualificationDTO> list = new ArrayList<>();
        list.add(new QualificationDTO("네트워크 관리사1급",
                12345678,
                LocalDate.of(2020,1,1),
                "네트워크협회",
                "1급",
                1L));
        assertTrue(qualificationService.addQualifications(list).size() > 0);
    }
}