package com.pado.inflow.payroll.query.repository;

import com.pado.inflow.payroll.query.dto.EmployeeInfoDTO;
import com.pado.inflow.payroll.query.dto.PeriodicPayDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SeverancePayMapper {
    // 직원 정보 조회
    EmployeeInfoDTO getEmployeeInfo(@Param("employeeId") Long employeeId);

    // 최근 3개월 급여 조회
    List<PeriodicPayDTO> getLastThreeMonthsPay(@Param("employeeId") Long employeeId, @Param("endDate") LocalDate endDate);

    // 입사일 조회
    LocalDate getJoinDateByEmployeeId(@Param("employeeId") Long employeeId);

    // 미사용 연차 조회
    Integer getUnusedLeaveDays(@Param("employeeId") Long employeeId);
}
