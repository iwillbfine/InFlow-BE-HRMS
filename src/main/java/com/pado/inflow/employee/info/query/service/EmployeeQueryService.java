package com.pado.inflow.employee.info.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
import com.pado.inflow.employee.info.query.dto.EmploymentCertificateDTO;
import com.pado.inflow.employee.info.query.repository.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("employeeQueryService")
public class EmployeeQueryService {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeQueryService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    //설명.1.1.  사원 리스트 전체 조회

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees = employeeMapper.findAllEmployees();
        if (employees.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }
        return employees;
    }

    //설명.1.2. 사원 리스트 이름으로 조회

    public List<EmployeeDTO> getEmployeesByName(String name) {
        List<EmployeeDTO> employees = employeeMapper.findEmployeesByName(name);
        if (employees.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }
        return employees;
    }

    //설명.1.3  사원 정보 사번으로 조회
    public EmployeeDTO getEmployeeByNumber(String employeeNumber) {
       EmployeeDTO employee =  employeeMapper.findEmployeeByNumber(employeeNumber);
        if (employee == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        return employee;
    }

    //설명.1.4 사원 정보 id로 조회
    public EmployeeDTO getEmployeeById(Long employeeId) {

        EmployeeDTO employee =employeeMapper.findEmployeeById(employeeId);
        if (employee == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }
        return employee;
    }

    // 설명.2.재직증명서 발급
    public EmploymentCertificateDTO getEmploymentCertificateInfo(String employeeNumber) {
        EmploymentCertificateDTO certificate = employeeMapper.getEmploymentCertificateInfo(employeeNumber);
        if (certificate == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_EMPLOYEE);
        }

        // 발급 날짜 추가
        certificate.setIssueDate(LocalDate.now().toString());

        // 내용 추가 (임의 값)
        certificate.setContent("본 재직증명서는 해당 사원이 현재 소속 회사에서 근무 중임을 증명합니다.");

        return certificate;
    }
}
