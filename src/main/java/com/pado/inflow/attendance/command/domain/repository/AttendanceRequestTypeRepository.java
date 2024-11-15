package com.pado.inflow.attendance.command.domain.repository;

import com.pado.inflow.attendance.command.domain.aggregate.entity.AttendanceRequestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRequestTypeRepository extends JpaRepository<AttendanceRequestType, Long> {
}
