package com.pado.inflow.statistics.query.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeNumServiceTests {

    private final EmployeeNumService employeeNumService;

    @Autowired
    public EmployeeNumServiceTests(EmployeeNumService employeeNumService) {
        this.employeeNumService = employeeNumService;
    }

    @DisplayName("전체 기간의 사원수 통계 조회")
    @Test
    public void getYearlyEmployeeNum() {
        assertTrue(employeeNumService.getYearlyEmpNums().size() > 0);
    }

    @DisplayName("특정 년도의 사원수 통계 조회")
    @ParameterizedTest
    @ValueSource(ints = {2023, 2022})
    public void getMonthlyEmployeeNum(int year) {
        assertTrue(employeeNumService.getOneYearEmpNums(year).size() > 0);
    }

}