package com.pado.inflow.statistics.query.repository;

import com.pado.inflow.statistics.query.dto.EmployeeNumDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeNumMapper {

    // 연도별 사원수 통계 조회
    List<EmployeeNumDTO> getAllYears(String year);
}
