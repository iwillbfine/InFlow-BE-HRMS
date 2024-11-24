package com.pado.inflow.department.command.application.service;

import com.pado.inflow.department.command.domain.aggregate.dto.AddDepartmentRequestDTO;
import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentDropdownDTO;
import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentResponseDTO;
import com.pado.inflow.department.command.domain.aggregate.dto.UpdateDepartmentRequestDTO;

import java.util.List;

public interface DepartmentCommandService {

    // 부서 드롭다운
    List<DepartmentDropdownDTO> getDepartmentsForDropdown();

    // 부서 추가
    DepartmentResponseDTO addDepartment(AddDepartmentRequestDTO addDepartmentRequestDTO);

//    // 부서 수정
//    DepartmentResponseDTO updateDepartment(UpdateDepartmentRequestDTO updateDepartmentRequestDTO);
//
//    // 부서 삭제
//    void deleteDepartment(Long departmentId);


}
