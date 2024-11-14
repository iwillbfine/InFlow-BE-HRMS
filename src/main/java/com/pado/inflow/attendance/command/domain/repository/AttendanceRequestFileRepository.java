package com.pado.inflow.attendance.command.domain.repository;

import com.pado.inflow.attendance.command.domain.aggregate.entity.AttendanceRequestFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRequestFileRepository extends JpaRepository<AttendanceRequestFile, Long> {
}
