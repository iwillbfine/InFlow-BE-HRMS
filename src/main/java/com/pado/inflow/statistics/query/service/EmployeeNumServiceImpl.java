package com.pado.inflow.statistics.query.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.statistics.query.dto.EmployeeNumDTO;
import com.pado.inflow.statistics.query.dto.YearlyEmployeeNumDTO;
import com.pado.inflow.statistics.query.repository.EmployeeNumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<YearlyEmployeeNumDTO> getYearlyEmpNums() {
        List<YearlyEmployeeNumDTO> result = new ArrayList<>();

        employeeNumMapper.getAllYears().forEach(yearly -> {
                    try {
                        result.add(new YearlyEmployeeNumDTO(yearly.getYear(),
                                objectMapper.readValue(yearly.getMonthlyData(),
                                new TypeReference<List<EmployeeNumDTO>>(){})));
                    } catch (JsonProcessingException e) {
                        throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
                    }
                }
        );

        return Optional.ofNullable(result)
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MONTHLY_EMPLOYEE_NUM_STATISTICS));
    }

    // 특정 년도의 월별 사원수 통계 조회
    public List<YearlyEmployeeNumDTO> getOneYearEmpNums(int yearNum) {
        List<YearlyEmployeeNumDTO> result = new ArrayList<>();

        employeeNumMapper.getOneYear(yearNum).forEach(yearly -> {
                    try {
                        result.add(new YearlyEmployeeNumDTO(yearly.getYear(),
                                objectMapper.readValue(yearly.getMonthlyData(),
                                        new TypeReference<List<EmployeeNumDTO>>(){})));
                    } catch (JsonProcessingException e) {
                        throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
                    }
                }
        );

        return Optional.ofNullable(result)
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MONTHLY_EMPLOYEE_NUM_STATISTICS));
    }
}
