package com.pado.inflow.vacation.command.domain.repository;

import com.pado.inflow.vacation.command.domain.aggregate.entity.VacationRequestFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRequestFileRepository extends JpaRepository<VacationRequestFile, Long> {
}
