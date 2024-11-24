package com.pado.inflow.department.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.department.query.dto.DepartmentMemberDTO;
import com.pado.inflow.department.query.service.DepartmentMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("departmentMemberQueryController")
@RequestMapping("/api/departmentMember")
public class DepartmentMemberController {

    private final DepartmentMemberService departmentMemberService;

    public DepartmentMemberController(DepartmentMemberService departmentMemberService) {
        this.departmentMemberService = departmentMemberService;
    }

    // 1. 부서구성원 조회
    @GetMapping("/{employeeId}")
    public ResponseDTO<?> getDepartmentMemberByEmployeeId(
            @PathVariable(value = "employeeId") Long employeeId
    ) {
        DepartmentMemberDTO selectedMember =
                departmentMemberService.findDepartmentMemberByEmployeeId(employeeId);
        return ResponseDTO.ok(selectedMember);

    }
}
