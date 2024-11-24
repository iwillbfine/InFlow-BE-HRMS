package com.pado.inflow.payroll.query.service;

import com.pado.inflow.payroll.query.dto.SeverancePayDTO;
import com.pado.inflow.payroll.query.dto.TotalWorkingDaysDTO;

import java.time.LocalDate;

public interface SeverancePayService {
    TotalWorkingDaysDTO getEstimateWorkingDays(Long employeeId);

    SeverancePayDTO calculateSeverancePay(Long employeeId);
}
