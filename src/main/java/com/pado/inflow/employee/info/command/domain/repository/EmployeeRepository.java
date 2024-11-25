package com.pado.inflow.employee.info.command.domain.repository;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.enums.ResignationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * 사번으로 사원 검색
     */
    Optional<Employee> findByEmployeeNumber(String employeeNumber);
    Optional<Employee> findByEmployeeId(Long employeeId);
    // 퇴사 여부로 조회
    Page<Employee> findByResignationStatus(ResignationStatus resignationStatus, Pageable pageable);

}
