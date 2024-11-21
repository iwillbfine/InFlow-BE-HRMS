package com.pado.inflow.department.query.controller;


import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.department.query.dto.GetDepartmentMemberDTO;
import com.pado.inflow.department.query.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("departmentQueryController")
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService){
            this.departmentService = departmentService;
    }


    // 1. 키워드 입력하여 요청 전송 API
    // keyword 입력하여 사원 목록 조회
    @GetMapping("/search/members")
    public ResponseDTO<List<String>> getEmployeesByKeyword(@RequestParam String keyword) {
        List<String> departmentMembers = departmentService.findEmployeesByKeyword(keyword);
        return ResponseDTO.ok(departmentMembers);
    }

    // 2. 부서 폴더 구조에서 해당 부서에 속하는 사원 목록
    @GetMapping("/code/{departmentCode}/members")
    public ResponseDTO<List<String>> getEmployeesByDepartmentCode(@PathVariable String departmentCode ){
        List<String> departmentMembers = departmentService.findEmployeesByDepartmentCode(departmentCode);
        return ResponseDTO.ok(departmentMembers);
    }

    // 3. 선택한 사원 상세 정보 조회
    // 공통 부서에 속한 사원들 목록 중 특정 사원 선택하면 해당 사원에 대한 상세 정보 조회가 가능하다
    @GetMapping("/employees/{employeeNumber}")
    public ResponseDTO<List<GetDepartmentMemberDTO>> getEmployeesByEmployeeNumber(@PathVariable String employeeNumber){
        List<GetDepartmentMemberDTO> departmentMemberDetail = departmentService.findEmployeeDetailByEmployeeNumber(employeeNumber);
        return ResponseDTO.ok(departmentMemberDetail);
    }


}
