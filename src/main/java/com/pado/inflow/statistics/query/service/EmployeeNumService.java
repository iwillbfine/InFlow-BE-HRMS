package com.pado.inflow.statistics.query.service;

import com.pado.inflow.statistics.query.dto.EmployeeNumDTO;

import java.util.List;

public interface EmployeeNumService {

    // 연도별 사원수 통계 조회
    List<EmployeeNumDTO> getYearlyEmpNums(String year);
}
