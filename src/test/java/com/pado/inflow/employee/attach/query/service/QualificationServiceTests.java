//package com.pado.inflow.employee.attach.query.service;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class QualificationServiceTests {
//    private final QualificationService qualificationService;
//
//    @Autowired
//    public QualificationServiceTests(QualificationService qualificationService) {
//        this.qualificationService = qualificationService;
//    }
//
//    @DisplayName("전 사원의 자격증 조회")
//    @Test
//    public void getAllQualifications() {
//        assertTrue(qualificationService.getQualificationAll().size() > 0);
//    }
//
//    @DisplayName("사원 한 명의 자격증 조회")
//    @ParameterizedTest
//    @ValueSource(longs = {1L, 2L})
//    public void getOneQualification(Long employeeId) {
//        assertTrue(qualificationService.getQualificationOne(employeeId).size() > 0);
//    }
//}