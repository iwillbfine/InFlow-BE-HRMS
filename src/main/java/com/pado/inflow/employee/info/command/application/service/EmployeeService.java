package com.pado.inflow.employee.info.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.request.RequestEmployeeDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.request.RequestUpdateEmployeeDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.dto.response.ResponseEmployeeDTO;
import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.command.domain.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("employeeCommandService")
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    // 추후 비밀번호 설정할 예정
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository
            , ModelMapper modelMapper
//            , BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
//        this.bCryptPasswordEncoder =bCryptPasswordEncoder;
    }

    /**
     * 설명. 1. 사원 등록
     */
    @Transactional
    public List<ResponseEmployeeDTO> registerEmployees(List<RequestEmployeeDTO> employeeDTOs) {
        //설명. DTO -> Entity 변환 및 저장
        List<Employee> employees = employeeDTOs.stream()
                .map(dto -> modelMapper.map(dto, Employee.class))
                .collect(Collectors.toList());
        
        //설명. Jpa의 saveAll통한 리스트 순차 저장
        List<Employee> savedEmployees = employeeRepository.saveAll(employees);

        //설명. Entity -> Response DTO 변환
        return savedEmployees.stream()
                .map(employee -> modelMapper.map(employee, ResponseEmployeeDTO.class))
                .collect(Collectors.toList());
    }
    //설명. 스프링 시큐리티 활성화 전 API 개발
//    @Transactional
//    public List<ResponseEmployeeDTO> registerEmployees(List<RequestEmployeeDTO> employeeDTOs) {
//        // DTO -> Entity 변환 및 비밀번호 암호화
//        List<Employee> employees = employeeDTOs.stream()
//                .map(dto -> {
//                    Employee employee = modelMapper.map(dto, Employee.class);
//                    // 비밀번호 암호화 처리
//                    employee.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
//                    return employee;
//                })
//                .collect(Collectors.toList());
//
//        // Jpa의 saveAll을 통한 리스트 순차 저장
//        List<Employee> savedEmployees = employeeRepository.saveAll(employees);
//
//        // Entity -> Response DTO 변환
//        return savedEmployees.stream()
//                .map(employee -> modelMapper.map(employee, ResponseEmployeeDTO.class))
//                .collect(Collectors.toList());
//    }


    /**
     * 설명. 2.1 사원 정보 수정 (ID 기준)
     */
    @Transactional
    public ResponseEmployeeDTO updateEmployeeById(Long employeeId, RequestUpdateEmployeeDTO updateEmployeeDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));

        updateEmployeeFields(employee, updateEmployeeDTO);

        //설명. jpa를 통한 수정
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, ResponseEmployeeDTO.class);
    }

    /**
     * 설명. 2.2 사원 정보 수정 (사번 기준)
     */
    @Transactional
    public ResponseEmployeeDTO updateEmployeeByEmployeeNumber(String employeeNumber, RequestUpdateEmployeeDTO updateEmployeeDTO) {
        Employee employee = employeeRepository.findByEmployeeNumber(employeeNumber)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE));
        
        updateEmployeeFields(employee, updateEmployeeDTO);
    
        //설명. jpa를 통한 수정
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, ResponseEmployeeDTO.class);
    }

    //설명. 사원 정보 업데이트시 공통 필드 업데이트 로직
    private void updateEmployeeFields(Employee employee, RequestUpdateEmployeeDTO updateEmployeeDTO) {
        // 설명. 요청 DTO 필드에 값이 있는 경우에만
        if (updateEmployeeDTO.getEmail() != null) {
            employee.setEmail(updateEmployeeDTO.getEmail());
        }
        if (updateEmployeeDTO.getPhoneNumber() != null) {
            employee.setPhoneNumber(updateEmployeeDTO.getPhoneNumber());
        }
        if (updateEmployeeDTO.getProfileImgUrl() != null) {
            employee.setProfileImgUrl(updateEmployeeDTO.getProfileImgUrl());
        }
        if (updateEmployeeDTO.getStreetAddress() != null) {
            employee.setStreetAddress(updateEmployeeDTO.getStreetAddress());
        }
        if (updateEmployeeDTO.getDetailedAddress() != null) {
            employee.setDetailedAddress(updateEmployeeDTO.getDetailedAddress());
        }
    }
}
