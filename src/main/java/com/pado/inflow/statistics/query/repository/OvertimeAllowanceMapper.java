package com.pado.inflow.statistics.query.repository;

import com.pado.inflow.statistics.query.dto.DeptOvertimeAllowanceDTO;
import com.pado.inflow.statistics.query.dto.OvertimeAllowanceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OvertimeAllowanceMapper {

    // 초과근무수당 통계 조회(연도)
    List<OvertimeAllowanceDTO> getAllOA(String year);

    // 초과근무수당 통계 조회(부서)
    List<DeptOvertimeAllowanceDTO> getDeptOA(String deptCode);
}
