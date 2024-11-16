package com.pado.inflow.employee.info.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.info.command.application.service.EmployeeService;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.request.RequestEmployeeDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseEmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("employeeCommandController")
@RequestMapping("/api/employee/employees")
public class EmployeeController {

    private final Environment env;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(Environment env,EmployeeService employeeService) {
        this.env = env;
        this.employeeService=employeeService;
    }
    
    // 설명. 0. 헬스 체크
    @GetMapping("/health")
    public String status() {
        return "임직원 정보 서비스 서버 헬스 체크 용 API (/api/employee-info) "
                + env.getProperty("local.server.port");
    }

    /* 설명. 1. 사원 등록
        사원 등록시 여러 사원이 등록 가능함. 따라서 요청 바디를 리스트 형식으로 요청
    */
    @PostMapping()
    public ResponseDTO<List<ResponseEmployeeDTO>> registerEmployees(
            @RequestBody List<RequestEmployeeDTO> employeeDTOs) {

        List<ResponseEmployeeDTO> createdEmployees = employeeService.registerEmployees(employeeDTOs);

        return ResponseDTO.ok(createdEmployees);

    }

    /* 설명. 2. 사원 기본 정보 수정
        사원 등록시 여러 사원이 등록 가능함. 따라서 요청 바디를 리스트 형식으로 요청
    */



}
