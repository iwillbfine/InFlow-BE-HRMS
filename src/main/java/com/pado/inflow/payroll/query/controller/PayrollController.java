package com.pado.inflow.payroll.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.query.dto.AllPaymentsDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;
import com.pado.inflow.payroll.query.dto.PayrollDTO;
import com.pado.inflow.payroll.query.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("queryPayrollController")
@RequestMapping("/api/payrolls")
public class PayrollController {

    private final PayrollService payrollService;

    @Autowired
    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // 사원의 월별 상세 급여 명세서 조회
    @GetMapping("/details/{employeeId}")
    public ResponseDTO<?> getPaymentByEmployeeIdAndYearAndMonth(@PathVariable Long employeeId,
                                                                @RequestParam("year") Integer year,
                                                                @RequestParam("month") Integer month) {
        PayrollDTO payment = payrollService.findPaymentDetail(employeeId, year, month);
        return ResponseDTO.ok(payment);
    }

    @GetMapping("/all/{employeeId}")
    public ResponseDTO<?> getAllPayments(@PathVariable Long employeeId,
                                         @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {
        PageDTO<AllPaymentsDTO> payments = payrollService.findPaymentsByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(payments);
    }


}
