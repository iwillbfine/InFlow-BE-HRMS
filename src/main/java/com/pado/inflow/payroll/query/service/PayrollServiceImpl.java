package com.pado.inflow.payroll.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.payroll.query.dto.PayrollDTO;
import com.pado.inflow.payroll.query.repository.PayrollMapper;
import org.springframework.stereotype.Service;

@Service
public class PayrollServiceImpl implements PayrollService {

    private final PayrollMapper payrollMapper;

    public PayrollServiceImpl(PayrollMapper payrollMapper) {
        this.payrollMapper = payrollMapper;
    }

    @Override
    public PayrollDTO getPaymentDetail(Long employeeId, Integer year, Integer month) {
       PayrollDTO payment = payrollMapper.findPaymentByEmployeeIdAndYearAndMonth(employeeId, year, month);
       if (payment == null) {
           throw new CommonException(ErrorCode.NOT_FOUND_PAYMENT);
       }
       return payment;
    }
}
