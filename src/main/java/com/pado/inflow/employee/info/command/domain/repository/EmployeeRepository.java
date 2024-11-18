package com.pado.inflow.employee.info.command.domain.repository;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository("employeeCommandRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * 사번으로 사원 검색
     */
    Optional<Employee> findByEmployeeNumber(String employeeNumber);
}
