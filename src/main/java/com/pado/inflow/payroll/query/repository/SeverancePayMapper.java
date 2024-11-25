package com.pado.inflow.payroll.query.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

@Mapper
public interface SeverancePayMapper {

    // 입사일 조회
    LocalDate getJoinDateByEmployeeId(@Param("employeeId") Long employeeId);

    // 총 급여 조회
    Integer getTotalSalary(@Param("employeeId") Long employeeId,
                           @Param("startDate") LocalDate startDate,
                           @Param("endDate") LocalDate endDate);

    // 총 비과세 급여 조회
    Integer getTotalNonTaxableSalary(@Param("employeeId") Long employeeId,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);

    // 연간 상여금 계산
    Integer getAnnualBonus(@Param("employeeId") Long employeeId,
                           @Param("oneYearAgo") LocalDate oneYearAgo,
                           @Param("endDate") LocalDate severanceDate);

    // 연차수당 계산
    Integer getLeaveAllowanceAddition(@Param("employeeId") Long employeeId);

    // 총 재직일 계산
    Long getTotalWorkingDays(@Param("employeeId") Long employeeId);

    // 통상임금 계산용 월급여 조회
    Integer getMonthlySalary(@Param("employeeId") Long employeeId);
}
