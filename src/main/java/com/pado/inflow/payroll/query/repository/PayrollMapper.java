package com.pado.inflow.payroll.query.repository;

import com.pado.inflow.payroll.query.dto.AllPaymentsDTO;
import com.pado.inflow.payroll.query.dto.PayrollDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayrollMapper {

    // 사원벌 연월별 급여 명세서 조회
    PayrollDTO findPaymentByEmployeeIdAndYearAndMonth(@Param("employeeId") Long employeeId,
                                                            @Param("year") Integer year,
                                                            @Param("month") Integer month);

    // 사원별 전체 급여 내역 조회
    List<AllPaymentsDTO> findPaymentsByEmployeeId(@Param("employeeId") Long employeeId,
                                                  @Param("elementsPerPage") Integer elementsPerPage,
                                                  @Param("offset") Integer offset);

    // 사원별 총 급여 내역 개수 조회
    Integer getTotalPaymentsByEmployeeId(Long employeeId);
}
