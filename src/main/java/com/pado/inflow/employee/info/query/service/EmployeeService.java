package com.pado.inflow.employee.info.query.service;

import com.pado.inflow.employee.info.query.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    List<EmployeeDTO> getEmployeesByName(String name);
    EmployeeDTO getEmployeeByNumber(String employeeNumber);
    EmployeeDTO getEmployeeById(Long employeeId);
}
