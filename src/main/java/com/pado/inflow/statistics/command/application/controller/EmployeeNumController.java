package com.pado.inflow.statistics.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.statistics.command.application.service.EmployeeNumService;
import com.pado.inflow.statistics.command.domain.aggregate.dto.EmployeeNumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("ENCommandController")
@RequestMapping("/api/statistics/employee")
public class EmployeeNumController {

    private final EmployeeNumService employeeNumService;

    @Autowired
    public EmployeeNumController(EmployeeNumService employeeNumService) {
        this.employeeNumService = employeeNumService;
    }

    // 사원수 통계 초기화
    @PostMapping
    public ResponseDTO<List<EmployeeNumDTO>> initEmployeeNum() {
        List<EmployeeNumDTO> result = employeeNumService.initEmployeeNum();
        return ResponseDTO.ok(result);
    }
}
