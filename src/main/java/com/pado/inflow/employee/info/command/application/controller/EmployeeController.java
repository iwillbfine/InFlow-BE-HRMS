package com.pado.inflow.employee.info.command.application.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("employeeInfo")
@RequestMapping("/api/employee/employees")
public class EmployeeController {

    private final Environment env;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(Environment env, ModelMapper modelMapper) {
        this.env = env;
        this.modelMapper=modelMapper;
    }

    @GetMapping("/health")
    public String status() {
        return "임직원 정보 서비스 서버 헬스 체크 용 API (/api/employee-info) "
                + env.getProperty("local.server.port");
    }

}
