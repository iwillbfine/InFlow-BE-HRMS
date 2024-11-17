package com.pado.inflow.statistics.query.repository;

import com.pado.inflow.statistics.query.dto.EmployeeNumMapperDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeNumMapper {

    // 전체 기간의 사원수 통계 조회
    List<EmployeeNumMapperDTO> getAllYears();

    // 특정 년도의 월별 사원수 통계 조회
    List<EmployeeNumMapperDTO> getOneYear(int yearNum);
}
