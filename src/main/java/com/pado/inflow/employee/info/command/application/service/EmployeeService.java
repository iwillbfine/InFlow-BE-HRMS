package com.pado.inflow.employee.info.command.application.service;

import com.pado.inflow.employee.info.command.domain.aggregate.dto.request.RequestEmployeeDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseEmployeeDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.command.domain.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("employeeCommandService")
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<ResponseEmployeeDTO> registerEmployees(List<RequestEmployeeDTO> employeeDTOs) {
        // DTO -> Entity 변환 및 저장
        List<Employee> employees = employeeDTOs.stream()
                .map(dto -> modelMapper.map(dto, Employee.class))
                .collect(Collectors.toList());

        List<Employee> savedEmployees = employeeRepository.saveAll(employees);

        // Entity -> Response DTO 변환
        return savedEmployees.stream()
                .map(employee -> modelMapper.map(employee, ResponseEmployeeDTO.class))
                .collect(Collectors.toList());
    }
}