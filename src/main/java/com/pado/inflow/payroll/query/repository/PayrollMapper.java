package com.pado.inflow.payroll.query.repository;

import com.pado.inflow.payroll.query.dto.PayrollDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PayrollMapper {

    // 사원벌 연월별 급여 명세서 조회
    PayrollDTO findPaymentByEmployeeIdAndYearAndMonth(@Param("employeeId") Long employeeId,
                                                            @Param("year") Integer year,
                                                            @Param("month") Integer month);
}
