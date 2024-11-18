package com.pado.inflow.vacation.command.domain.repository;

import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationPolicyRepository extends JpaRepository<VacationPolicy, Long> {
    List<VacationPolicy> findByYearAndAutoAllocationCycleIsNotNull(int currentYear);
}
