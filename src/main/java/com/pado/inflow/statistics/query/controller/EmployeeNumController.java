package com.pado.inflow.statistics.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.statistics.query.dto.EmployeeNumDTO;
import com.pado.inflow.statistics.query.service.EmployeeNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ENQueryController")
@RequestMapping("/api/statistics/employee")
public class EmployeeNumController {

    private final EmployeeNumService employeeNumService;

    @Autowired
    public EmployeeNumController(EmployeeNumService employeeNumService) {
        this.employeeNumService = employeeNumService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm healthy";
    }

    // 전체 기간의 사원수 통계 조회
    @GetMapping
    public ResponseDTO getYearlyEmployeeNum() {
        List<EmployeeNumDTO> result = employeeNumService.getYearlyEmpNums();
        return ResponseDTO.ok(result);
    }

    // 특정 년도의 사원수 통계 조회
    @GetMapping("/{year}")
    public ResponseDTO getMonthlyEmployeeNum(@PathVariable("year") String year) {
        List<EmployeeNumDTO> result = employeeNumService.getOneYearEmpNums(year);
        return ResponseDTO.ok(result);
    }
}