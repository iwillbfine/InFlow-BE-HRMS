package com.pado.inflow.payroll.query.repository;

import com.pado.inflow.payroll.query.dto.PeriodicPayDTO;
import com.pado.inflow.payroll.query.dto.SeverancePayDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

@Mapper
public interface SeverancePayMapper {

    // 퇴직금 계산을 위한 데이터 조회
    SeverancePayDTO getSeveranceData(Long employeeId, LocalDate referenceDate);
}
