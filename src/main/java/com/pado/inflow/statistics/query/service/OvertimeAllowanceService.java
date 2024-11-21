package com.pado.inflow.statistics.query.service;

import com.pado.inflow.statistics.query.dto.DeptOvertimeAllowanceDTO;
import com.pado.inflow.statistics.query.dto.OvertimeAllowanceDTO;

import java.util.List;

public interface OvertimeAllowanceService {

    // 초과근무수당 통계 조회(연도)
    List<OvertimeAllowanceDTO> getAllOAStats(String year);

    // 초과근무수당 통계 조회(부서)
    List<DeptOvertimeAllowanceDTO> getDeptOAStats(String deptCode);
}
