package com.pado.inflow.statistics.command.domain.repository;

import com.pado.inflow.statistics.command.domain.aggregate.entity.EmployeeNum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeNumRepository extends JpaRepository<EmployeeNum, Long> {
}
