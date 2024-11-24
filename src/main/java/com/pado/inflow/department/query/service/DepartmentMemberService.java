package com.pado.inflow.department.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.department.query.dto.DepartmentMemberDTO;
import com.pado.inflow.department.query.repository.DepartmentMemberMapper;
import org.springframework.stereotype.Service;

@Service("QueryDepartmentMemberService")
public class DepartmentMemberService {

    private final DepartmentMemberMapper departmentMemberMapper;

    public DepartmentMemberService(DepartmentMemberMapper departmentMemberMapper) {
        this.departmentMemberMapper = departmentMemberMapper;
    }
    public DepartmentMemberDTO findDepartmentMemberByEmployeeId(Long employeeId) {

        DepartmentMemberDTO selectedMember =
                departmentMemberMapper.findDepartmentMemberByEmployeeId(employeeId);

        if (selectedMember == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT_MEMBER);
        }
        return selectedMember;
    }
}
