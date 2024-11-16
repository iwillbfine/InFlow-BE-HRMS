package com.pado.inflow.employee.info.command.domain.repository;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository("employeeCommandRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // 추가적으로 필요한 커스텀 쿼리 메서드를 정의할 수 있음
}
