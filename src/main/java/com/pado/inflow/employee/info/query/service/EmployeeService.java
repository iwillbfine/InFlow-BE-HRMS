package com.pado.inflow.employee.info.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import com.pado.inflow.employee.info.query.repository.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeQueryService")
public class EmployeeService {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    // 1.1. 설명: 사원 리스트 전체 조회

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees = employeeMapper.findAllEmployees();
        if (employees.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }
        return employees;
    }

    // 1.2. 설명: 사원 리스트 이름으로 조회

    public List<EmployeeDTO> getEmployeesByName(String name) {
        List<EmployeeDTO> employees = employeeMapper.findEmployeesByName(name);
        if (employees.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }
        return employees;
    }

    // 1.3. 설명: 사원 정보 사번으로 조회
    public EmployeeDTO getEmployeeByNumber(String employeeNumber) {
        return employeeMapper.findEmployeeByNumber(employeeNumber)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));
    }

    // 1.4. 설명: 사원 정보 id로 조회

    public EmployeeDTO getEmployeeById(Long employeeId) {
        return employeeMapper.findEmployeeById(employeeId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));
    }
}
