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
//class CareerServiceTests {
//
//    private final CareerService careerService;
//
//    @Autowired
//    public CareerServiceTests(CareerService careerService) {
//        this.careerService = careerService;
//    }
//
//    @DisplayName("전 사원의 경력 조회")
//    @Test
//    public void getAllCareers() {
//        assertTrue(careerService.getCareerAll().size() > 0);
//    }
//
//    @DisplayName("사원 한 명의 경력 조회")
//    @ParameterizedTest
//    @ValueSource(longs = {1L, 2L})
//    public void getOneCareer(Long employeeId) {
//        assertTrue(careerService.getCareerOne(employeeId).size() > 0);
//    }
//}