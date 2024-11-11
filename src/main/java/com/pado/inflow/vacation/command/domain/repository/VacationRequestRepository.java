package com.pado.inflow.vacation.command.domain.repository;

import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
}
