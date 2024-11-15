package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.QualificationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
                "12345678",
                LocalDate.of(2020,1,1),
                "네트워크협회",
                "1급",
                1L));
        assertTrue(qualificationService.addQualifications(list).size() > 0);
    }

    @DisplayName("사원의 자격증 정보 수정")
    @Test
    public void modifyQualification() {
        List<QualificationDTO> list = new ArrayList<>();
        list.add(new QualificationDTO(1L,
                "정보처리산업기사",
                "123456789",
                LocalDate.of(2023,12,1),
                "한국산업인력공단",
                "PASS",
                1L));
        list.add(new QualificationDTO(2L,
                "컴퓨터활용능력2",
                "987654321",
                LocalDate.of(2024,1,15),
                "한국산업인력공단",
                "PASS",
                2L));
        assertTrue(qualificationService.modifyQualifications(list).size() > 0);
    }

    @DisplayName("사원의 자격증 정보 삭제")
    @Test
    public void deleteQualification() {
        assertTrue(qualificationService.deleteQualifications(new ArrayList<>(Arrays.asList(1L, 2L, 3L))));
    }
}
