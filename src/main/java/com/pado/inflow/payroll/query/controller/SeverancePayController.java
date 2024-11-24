package com.pado.inflow.payroll.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.query.dto.SeverancePayDTO;
import com.pado.inflow.payroll.query.dto.TotalWorkingDaysDTO;
import com.pado.inflow.payroll.query.service.SeverancePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController("querySeverancePayController")
@RequestMapping("/api/severance-pay")
public class SeverancePayController {

    private final SeverancePayService severancePayService;

    @Autowired
    public SeverancePayController(SeverancePayService severancePayService) {
        this.severancePayService = severancePayService;
    }

    // 퇴직금 예상치 조회 탭 이동 시 총 재직일 수 계산하는 api
    @GetMapping("/estimate/{employeeId}")
    public ResponseDTO<?> getEstimateWorkingDays(@PathVariable Long employeeId) {
        TotalWorkingDaysDTO totalWorkingDaysDTO = severancePayService.getEstimateWorkingDays(employeeId);
        return ResponseDTO.ok(totalWorkingDaysDTO);
    }

    // 퇴직금 계산
    @GetMapping("/calculate/{employeeId}")
    public ResponseDTO<?> calculateSeverancePay(@PathVariable Long employeeId) {
        SeverancePayDTO severancePayDTO = severancePayService.calculateSeverancePay(employeeId);
        return ResponseDTO.ok(severancePayDTO);
    }

}
