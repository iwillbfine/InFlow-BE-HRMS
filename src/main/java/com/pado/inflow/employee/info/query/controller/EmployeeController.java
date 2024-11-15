package com.pado.inflow.employee.info.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import com.pado.inflow.employee.info.query.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController("employeeQueryController")
@RequestMapping("/api/employee/employees")
public class EmployeeController {
    private final Environment env;
    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(Environment env, ModelMapper modelMapper, EmployeeService employeeService) {
        this.env = env;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }

    // 1.1. 설명: 사원 리스트 전체 조회
    @GetMapping("/")
    public ResponseDTO<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTO = employeeService.getAllEmployees();
        return ResponseDTO.ok(employeeDTO);
    }

    // 1.2. 설명: 사원 리스트 이름으로 조회
    @GetMapping("/name")
    public ResponseDTO<List<EmployeeDTO>> getEmployeesByName(@RequestParam String name) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByName(name);
        return ResponseDTO.ok(employees);
    }

    // 1.3. 설명: 사원 정보 사번으로 조회
    @GetMapping("/number/{employeeNumber}")
    public ResponseDTO<EmployeeDTO> getEmployeeByNumber(@PathVariable String employeeNumber) {
        EmployeeDTO employee = employeeService.getEmployeeByNumber(employeeNumber);
        return ResponseDTO.ok(employee);
    }

    // 1.4. 설명: 사원 정보 id로 조회
    @GetMapping("/id/{employeeId}")
    public ResponseDTO<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
        return ResponseDTO.ok(employee);
    }
}
