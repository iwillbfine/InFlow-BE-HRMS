package com.pado.inflow.statistics.command.domain.repository;

import com.pado.inflow.statistics.command.domain.aggregate.entity.OvertimeAllowance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OvertimeAllowanceRepository extends JpaRepository<OvertimeAllowance, Long> {
}
