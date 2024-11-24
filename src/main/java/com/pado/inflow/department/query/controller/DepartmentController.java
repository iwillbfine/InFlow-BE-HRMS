package com.pado.inflow.department.query.controller;


import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.department.query.dto.DepartmentMemberDTO;
import com.pado.inflow.department.query.dto.GetDepartmentDetailDTO;
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



    // 1. 검색창을 통한 사원 목록 조회, 부서 폴더를 통한 사원 목록 조회
    @GetMapping("/search/members")
    public ResponseDTO<List<GetDepartmentMemberDTO>> getEmployeesByKeywordOrDepartmentCode(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String departmentCode) {

        if (keyword != null) {
            // 키워드 기반 조회
            return ResponseDTO.ok(departmentService.findEmployeesByKeyword(keyword));
        } else if (departmentCode != null) {
            // 부서 코드 기반 조회
            return ResponseDTO.ok(departmentService.findEmployeesByDepartmentCode(departmentCode));
        } else {
            // 둘 다 없는 경우 예외 처리
            throw new IllegalArgumentException("keyword 또는 departmentCode 중 하나를 반드시 제공해야 합니다.");
        }
    }

    // 3. 선택한 사원 상세 정보 조회
    // 공통 부서에 속한 사원들 목록 중 특정 사원 선택하면 해당 사원에 대한 상세 정보 조회가 가능하다
    @GetMapping("/search/members/detail/employee-code/{employeeNumber}")
    public ResponseDTO<List<GetDepartmentMemberDTO>> getEmployeesByEmployeeNumber(@PathVariable String employeeNumber){
        List<GetDepartmentMemberDTO> departmentMemberDetail = departmentService.findEmployeeDetailByEmployeeNumber(employeeNumber);
        return ResponseDTO.ok(departmentMemberDetail);
    }


    /* 인사팀 권한 - 부서 관리 */
    // 1. 인사팀의 부서 상세정보 조회 -by 부서코드
    @GetMapping("/detail/department-code/{departmentCode}")
    public ResponseDTO<List<GetDepartmentDetailDTO>> getDepartmentDetailByDepartmentCode(@PathVariable String departmentCode){
        List<GetDepartmentDetailDTO> departmentDetail = departmentService.findDepartmentDetailByDepartmentCode(departmentCode);
        return ResponseDTO.ok(departmentDetail);
    }



}
