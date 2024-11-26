package com.pado.inflow.statistics.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.statistics.command.application.service.OvertimeAllowanceService;
import com.pado.inflow.statistics.command.domain.aggregate.dto.OvertimeAllowanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("OACommandController")
@RequestMapping("/api/statistics/overtime-allowance")
public class OvertimeAllowanceController {

    private final OvertimeAllowanceService overtimeAllowanceService;

    @Autowired
    public OvertimeAllowanceController(OvertimeAllowanceService overtimeAllowanceService) {
        this.overtimeAllowanceService = overtimeAllowanceService;
    }

    // 초과근무수당 통계 생성(초기화)
    @PostMapping
    public ResponseDTO<List<OvertimeAllowanceDTO>> initOvertimeAllowance() {
        List<OvertimeAllowanceDTO> result = overtimeAllowanceService.initOvertimeAllowance();
        return ResponseDTO.ok(result);
    }
}
