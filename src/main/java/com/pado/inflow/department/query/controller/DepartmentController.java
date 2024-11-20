package com.pado.inflow.department.query.controller;


import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.department.query.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    // return 은 JSON으로 응답
    @GetMapping("/members/search")
    public ResponseDTO<List<String>> searchEmployees(@RequestParam String keyword) {
        List<String> departmentMembers = departmentService.findEmployeesByKeyword(keyword);
        return ResponseDTO.ok(departmentMembers); // JSON 형식으로 응답
    }

}
