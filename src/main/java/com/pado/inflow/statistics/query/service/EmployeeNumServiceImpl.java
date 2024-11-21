package com.pado.inflow.statistics.query.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.statistics.query.dto.EmployeeNumDTO;
import com.pado.inflow.statistics.query.repository.EmployeeNumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ENQueryService")
public class EmployeeNumServiceImpl implements EmployeeNumService {

    private final EmployeeNumMapper employeeNumMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeNumServiceImpl(EmployeeNumMapper employeeNumMapper,
                                  ObjectMapper objectMapper) {
        this.employeeNumMapper = employeeNumMapper;
        this.objectMapper = objectMapper;
    }

    // 전체 기간의 사원수 통계 조회
    public List<EmployeeNumDTO> getYearlyEmpNums() {
        return Optional.ofNullable(employeeNumMapper.getAllYears())
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MONTHLY_EMPLOYEE_NUM_STATISTICS));
    }

    // 특정 년도의 월별 사원수 통계 조회
    public List<EmployeeNumDTO> getOneYearEmpNums(String year) {
        return Optional.ofNullable(employeeNumMapper.getOneYear(year))
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MONTHLY_EMPLOYEE_NUM_STATISTICS));
    }
}
