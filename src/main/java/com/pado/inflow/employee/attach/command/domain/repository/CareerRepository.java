package com.pado.inflow.employee.attach.command.domain.repository;

import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {
}
