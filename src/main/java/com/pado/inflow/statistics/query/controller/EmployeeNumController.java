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

    // 연도별 사원수 통계 조회
    @GetMapping
    public ResponseDTO<List<EmployeeNumDTO>> getYearlyEmployeeNum() {
        List<EmployeeNumDTO> result = employeeNumService.getYearlyEmpNums(null);
        return ResponseDTO.ok(result);
    }

    // 특정 년도의 사원수 통계 조회
    @GetMapping("/{year}")
    public ResponseDTO<List<EmployeeNumDTO>> getMonthlyEmployeeNum(@PathVariable("year") String year) {
        List<EmployeeNumDTO> result = employeeNumService.getYearlyEmpNums(year);
        return ResponseDTO.ok(result);
    }
}