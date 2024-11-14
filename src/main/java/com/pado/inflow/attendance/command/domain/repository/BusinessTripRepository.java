package com.pado.inflow.attendance.command.domain.repository;

import com.pado.inflow.attendance.command.domain.aggregate.entity.BusinessTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessTripRepository extends JpaRepository<BusinessTrip, Long> {
}
