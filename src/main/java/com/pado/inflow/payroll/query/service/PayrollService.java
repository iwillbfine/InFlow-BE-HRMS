package com.pado.inflow.payroll.query.service;

import com.pado.inflow.payroll.query.dto.PayrollDTO;

public interface PayrollService {
    PayrollDTO getPaymentDetail(Long employeeId, Integer year, Integer month);
}
