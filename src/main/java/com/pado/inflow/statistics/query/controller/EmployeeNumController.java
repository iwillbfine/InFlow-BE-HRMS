package com.pado.inflow.statistics.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.statistics.query.dto.YearlyEmployeeNumDTO;
import com.pado.inflow.statistics.query.service.EmployeeNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<YearlyEmployeeNumDTO> result = employeeNumService.getYearlyEmpNums();
        return ResponseDTO.ok(result);
    }

    // 전체 기간의 사원수 통계 조회
    @GetMapping("/{yearNum}")
    public ResponseDTO getMonthlyEmployeeNum(@PathVariable("yearNum") int yearNum) {
        List<YearlyEmployeeNumDTO> result = employeeNumService.getOneYearEmpNums(yearNum);
        return ResponseDTO.ok(result);
    }
}
