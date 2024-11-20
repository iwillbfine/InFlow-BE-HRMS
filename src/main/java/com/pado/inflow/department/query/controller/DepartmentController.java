package com.pado.inflow.department.query.controller;


import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.department.query.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("departmentQueryController")
@RequestMapping("/api/departments")
public class DepartmentController {
    // myBatis에서 작성한 xml 쿼리를 호출
    // 컨트롤러는 클라이언트 요청을 받아 데이터를 전달하는 역할

    private final DepartmentService departmentService;  // 서비스 계층에서 만든 서비스 객체 호출

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
    // 만약 상위 부서를 클릭하면, 상위 부서에 속하는 모든 사원 목록 조회
    // 만약 하위 부서를 클릭하면, 하위 부서에 속하는 모든 사원 목록 조회
    // 계층 구조 이용하기 -> 단 검색 조건이 like 가 아님 (1번 서비스와 동일하게 하면 될듯)
    @GetMapping("/code/{departmentCode}/employees")
    public ResponseDTO<List<String>> getEmployeesByDepartmentCode(@PathVariable String departmentCode ){
        List<String> departmentMembers = departmentService.findEmployeesByDepartmentCode(departmentCode);
        return ResponseDTO.ok(departmentMembers);
    }

}
