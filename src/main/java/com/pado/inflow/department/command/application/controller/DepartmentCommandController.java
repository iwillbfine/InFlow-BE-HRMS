package com.pado.inflow.department.command.application.controller;


import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.department.command.application.service.DepartmentCommandService;
import com.pado.inflow.department.command.domain.aggregate.dto.AddDepartmentRequestDTO;
import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentDropdownDTO;
import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentResponseDTO;
import org.springframework.web.bind.annotation.*;

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

    // 부서 추가
    @PostMapping("/add-department")
    public ResponseDTO<DepartmentResponseDTO> addDepartment(
            @RequestBody AddDepartmentRequestDTO addDepartmentRequestDTO
    ) {
        System.out.println("Request DTO from Controller: " + addDepartmentRequestDTO);
        DepartmentResponseDTO addedDepartment = departmentService.addDepartment(addDepartmentRequestDTO);
        return ResponseDTO.ok(addedDepartment);
    }
}
