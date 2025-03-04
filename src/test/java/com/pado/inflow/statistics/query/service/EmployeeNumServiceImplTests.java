//package com.pado.inflow.statistics.query.service;
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
//class EmployeeNumServiceImplTests {
//
//    private final EmployeeNumService employeeNumService;
//
//    @Autowired
//    public EmployeeNumServiceImplTests(EmployeeNumService employeeNumService) {
//        this.employeeNumService = employeeNumService;
//    }
//
//    @DisplayName("전체 기간의 사원수 통계 조회")
//    @Test
//    public void getYearlyEmployeeNum() {
//        assertTrue(employeeNumService.getYearlyEmpNums(null).size() > 0);
//    }
//
//    @DisplayName("특정 년도의 사원수 통계 조회")
//    @ParameterizedTest
//    @ValueSource(strings = {"2023", "2022"})
//    public void getMonthlyEmployeeNum(String year) {
//        assertTrue(employeeNumService.getYearlyEmpNums(year).size() > 0);
//    }
//
//}