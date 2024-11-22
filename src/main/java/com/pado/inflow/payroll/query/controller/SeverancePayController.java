package com.pado.inflow.payroll.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.query.dto.SeverancePayDTO;
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

    @GetMapping("/severance-pay")
    public ResponseDTO<?> getSeverancePay(@PathVariable Long employeeId,
                                          @RequestParam("referenceDate") LocalDate referenceDate) {

        if (referenceDate == null) {
            referenceDate = LocalDate.now();
        }

        SeverancePayDTO severancePay = severancePayService.calculateSeverancePay(employeeId, referenceDate);
        return ResponseDTO.ok(severancePay);
    }


}
