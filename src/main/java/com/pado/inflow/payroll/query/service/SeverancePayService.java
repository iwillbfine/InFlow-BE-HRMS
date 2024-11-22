package com.pado.inflow.payroll.query.service;

import com.pado.inflow.payroll.query.dto.SeverancePayDTO;

import java.time.LocalDate;

public interface SeverancePayService {
    SeverancePayDTO calculateSeverancePay(Long employeeId, LocalDate referenceDate);
}
