package com.pado.inflow.payroll.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.query.dto.AllPaymentsDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;
import com.pado.inflow.payroll.query.dto.PayrollDTO;
import com.pado.inflow.payroll.query.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 사원의 기간별 급여 내역 조회
    @GetMapping("/all/{employeeId}")
    public ResponseDTO<?> getAllPayments(@PathVariable Long employeeId,
                                         @RequestParam(value = "page", defaultValue = "1") Integer pageNo) {
        PageDTO<AllPaymentsDTO> payments = payrollService.findPaymentsByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(payments);
    }

    // 사원의 연도별 급여 내역 조회
    @GetMapping("/list")
    public ResponseDTO<?> getPaymentsByYear(@RequestParam("employeeId") Long employeeId,
                                            @RequestParam("year") int year) {
        List<AllPaymentsDTO> payments = payrollService.findPaymentsByYear(employeeId, year);
        return ResponseDTO.ok(payments);
    }

    @GetMapping("/period")
    public ResponseDTO<?> getPeriodicPayments(@RequestParam("employeeId") Long employeeId,
                                              @RequestParam("startDate") String startDate,
                                              @RequestParam("endDate") String endDate) {
        List<AllPaymentsDTO> payments = payrollService.findPeriodicPayments(employeeId, startDate, endDate);
        return ResponseDTO.ok(payments);
    }


}
