package com.pado.inflow.statistics.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.statistics.query.dto.DeptOvertimeAllowanceDTO;
import com.pado.inflow.statistics.query.dto.OvertimeAllowanceDTO;
import com.pado.inflow.statistics.query.service.OvertimeAllowanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("OAQueryController")
@RequestMapping("/api/statistics/overtime-allowance")
public class OvertimeAllowanceController {

    private final OvertimeAllowanceService overtimeAllowanceService;

    @Autowired
    public OvertimeAllowanceController(OvertimeAllowanceService overtimeAllowanceService) {
        this.overtimeAllowanceService = overtimeAllowanceService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm OK!!";
    }


    // 초과근무수당 통계 조회
    @GetMapping
    public ResponseDTO getOvertimeAllowance() {
        List<OvertimeAllowanceDTO> result = overtimeAllowanceService.getAllOAStats(null);
        return ResponseDTO.ok(result);
    }

    // 연도 초과근무수당 통계 조회
    @GetMapping("/year/{year}")
    public ResponseDTO getYearlyOvertimeAllowance(@PathVariable("year") String year) {
        List<OvertimeAllowanceDTO> result = overtimeAllowanceService.getAllOAStats(year);
        return ResponseDTO.ok(result);
    }

    // 부서 초과근무수당 통계 조회
    @GetMapping("/department/{deptCode}")
    public ResponseDTO getDeptOvertimeAllowance(@PathVariable("deptCode") String deptCode) {
        List<DeptOvertimeAllowanceDTO> result = overtimeAllowanceService.getDeptOAStats(deptCode);
        return ResponseDTO.ok(result);
    }
}
