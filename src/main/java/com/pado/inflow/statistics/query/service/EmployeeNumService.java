package com.pado.inflow.statistics.query.service;

import com.pado.inflow.statistics.query.dto.YearlyEmployeeNumDTO;

import java.util.List;

public interface EmployeeNumService {

    // 전체 기간의 사원수 통계 조회
    List<YearlyEmployeeNumDTO> getYearlyEmpNums();

    // 특정 년도의 월별 사원수 통계 조회
    List<YearlyEmployeeNumDTO> getOneYearEmpNums(int yearNum);
}
