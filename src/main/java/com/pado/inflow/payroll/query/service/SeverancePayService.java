package com.pado.inflow.payroll.query.service;

import com.pado.inflow.payroll.query.dto.SeverancePayDetailsDTO;
import com.pado.inflow.payroll.query.dto.TotalWorkingDaysDTO;

public interface SeverancePayService {
    TotalWorkingDaysDTO getEstimateWorkingDays(Long employeeId);

    SeverancePayDetailsDTO calculateSeverancePayDetails(Long employeeId);
}
