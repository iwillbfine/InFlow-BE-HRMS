package com.pado.inflow.department.command.domain.repository;

import com.pado.inflow.department.command.domain.aggregate.dto.DepartmentDropdownDTO;
import com.pado.inflow.department.command.domain.aggregate.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.beans.JavaBean;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    // 드롭다운
    // 부서 코드와 부서명만 반환하도록 메서드 정의
    @Query("SELECT new com.pado.inflow.department.command.domain.aggregate.dto.DepartmentDropdownDTO(d.departmentCode, d.departmentName) FROM Department d")
    List<DepartmentDropdownDTO> findAllForDropdown();
//
//    // 부서 삭제
//    void deleteDepartmentByDepartmentCode(Long departmentCode);


}
