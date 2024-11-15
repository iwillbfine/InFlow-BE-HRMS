package com.pado.inflow.attendance.command.domain.repository;

import com.pado.inflow.attendance.command.domain.aggregate.entity.LeaveReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveReturnRepository extends JpaRepository<LeaveReturn, Long> {
}
