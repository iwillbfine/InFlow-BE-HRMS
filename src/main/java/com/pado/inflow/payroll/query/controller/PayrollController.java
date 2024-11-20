package com.pado.inflow.payroll.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.query.dto.PayrollDTO;
import com.pado.inflow.payroll.query.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {

    private final PayrollService payrollService;

    @Autowired
    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // 사원의 월별 상세 급여 명세서 조회
    @GetMapping("/details")
    public ResponseDTO<?> getPaymentByEmployeeIdAndYearAndMonth(@RequestParam("employeeId") Long employeeId,
                                                                @RequestParam("year") Integer year,
                                                                @RequestParam("month") Integer month) {
        PayrollDTO payment = payrollService.getPaymentDetail(employeeId, year, month);
        return ResponseDTO.ok(payment);
    }


}
