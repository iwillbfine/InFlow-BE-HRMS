package com.pado.inflow.payroll.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.query.dto.SeverancePayDTO;
import com.pado.inflow.payroll.query.dto.TotalWorkindDayDTO;
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
    @GetMapping("/estimate")
    public ResponseDTO<?> getEstimateWorkingDays(@PathVariable Long employeeId,
                                          @RequestParam("referenceDate") LocalDate referenceDate) {

        if (referenceDate == null) {
            referenceDate = LocalDate.now();
        }

        TotalWorkindDayDTO totalWorkindDayDTO = severancePayService.getEstimateWorkindDays(employeeId, referenceDate);
        return ResponseDTO.ok(totalWorkindDayDTO);
    }

    // 재직일 수 계산 후 화면에 생긴 버튼 클릭 시 실행되는 퇴직금 계산 api



}
