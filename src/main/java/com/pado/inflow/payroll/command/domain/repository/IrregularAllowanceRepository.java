package com.pado.inflow.payroll.command.domain.repository;

import com.pado.inflow.payroll.command.domain.aggregate.entity.IrregularAllowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrregularAllowanceRepository extends JpaRepository<IrregularAllowance, Long> {
}
