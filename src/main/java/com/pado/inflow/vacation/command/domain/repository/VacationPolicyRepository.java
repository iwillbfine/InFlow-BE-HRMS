package com.pado.inflow.vacation.command.domain.repository;

import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationPolicyRepository extends JpaRepository<VacationPolicy, Long> {
}
