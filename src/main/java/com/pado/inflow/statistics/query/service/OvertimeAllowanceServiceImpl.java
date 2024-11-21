package com.pado.inflow.statistics.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.statistics.query.dto.DeptOvertimeAllowanceDTO;
import com.pado.inflow.statistics.query.dto.OvertimeAllowanceDTO;
import com.pado.inflow.statistics.query.repository.OvertimeAllowanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("OAQueryService")
public class OvertimeAllowanceServiceImpl implements OvertimeAllowanceService {

    private final OvertimeAllowanceMapper overtimeAllowanceMapper;

    @Autowired
    public OvertimeAllowanceServiceImpl(OvertimeAllowanceMapper overtimeAllowanceMapper) {
        this.overtimeAllowanceMapper = overtimeAllowanceMapper;
    }

    // 초과근무수당 통계 조회(연도)
    @Override
    public List<OvertimeAllowanceDTO> getAllOAStats(String year) {
        return Optional.ofNullable(overtimeAllowanceMapper.getAllOA(year))
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MONTHLY_DEPARTMENT_OVERTIME_ALLOWANCE_STATISTICS));
    }

    // 초과근무수당 통계 조회(부서)
    @Override
    public List<DeptOvertimeAllowanceDTO> getDeptOAStats(String deptCode) {
        return Optional.ofNullable(overtimeAllowanceMapper.getDeptOA(deptCode))
                .filter(num -> !num.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MONTHLY_DEPARTMENT_OVERTIME_ALLOWANCE_STATISTICS));
    }
}
