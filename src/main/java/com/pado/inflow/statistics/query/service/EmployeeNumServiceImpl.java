package com.pado.inflow.statistics.query.service;

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

    @Autowired
    public EmployeeNumServiceImpl(EmployeeNumMapper employeeNumMapper) {
        this.employeeNumMapper = employeeNumMapper;
    }

    // 연도별 사원수 통계 조회
    @Override
    public List<EmployeeNumDTO> getYearlyEmpNums(String year) {
        return Optional.ofNullable(employeeNumMapper.getAllYears(year))
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MONTHLY_EMPLOYEE_NUM_STATISTICS));
    }
}
