package com.pado.inflow.statistics.query.service;

import com.pado.inflow.statistics.query.dto.EmployeeNumDTO;

import java.util.List;

public interface EmployeeNumService {

    // 전체 기간의 사원수 통계 조회
    List<EmployeeNumDTO> getYearlyEmpNums();

    // 특정 년도의 월별 사원수 통계 조회
    List<EmployeeNumDTO> getOneYearEmpNums(String year);
}
