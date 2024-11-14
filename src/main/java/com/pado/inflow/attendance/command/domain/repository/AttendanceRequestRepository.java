package com.pado.inflow.attendance.command.domain.repository;

import com.pado.inflow.attendance.command.domain.aggregate.entity.AttendanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRequestRepository extends JpaRepository<AttendanceRequest, Long> {
}
