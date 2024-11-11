package com.pado.inflow.vacation.command.domain.repository;

import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationTypeRepository extends JpaRepository<VacationType, Long> {
}
