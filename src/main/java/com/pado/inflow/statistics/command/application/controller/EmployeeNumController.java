package com.pado.inflow.statistics.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.statistics.command.application.service.EmployeeNumService;
import com.pado.inflow.statistics.command.domain.aggregate.entity.EmployeeNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping
    public ResponseDTO initEmployeeNum() {
        List<EmployeeNum> result = employeeNumService.initEmployeeNum();
        return ResponseDTO.ok(result);
    }
}
