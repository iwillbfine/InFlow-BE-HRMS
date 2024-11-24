package com.pado.inflow.department.query.repository;

import com.pado.inflow.department.query.dto.DepartmentMemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMemberMapper {
    DepartmentMemberDTO findDepartmentMemberByEmployeeId(Long employeeId);
}
