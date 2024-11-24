package com.pado.inflow.department.command.application.controller;


import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.department.command.application.service.DepartmentCommandService;
import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentDropdownDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("CommandDepartmentController")
@RequestMapping("/api/departments")

public class DepartmentCommandController {

    private final DepartmentCommandService departmentService;

    public DepartmentCommandController(DepartmentCommandService departmentService){
        this.departmentService = departmentService;
    }

    // 드롭다운 구현을 위한 API
    @GetMapping("/dropdown")
    public ResponseDTO<List<DepartmentDropdownDTO>> getDepartmentsForDropdown() {
        List<DepartmentDropdownDTO> departments = departmentService.getDepartmentsForDropdown();
        return ResponseDTO.ok(departments);
    }
}
