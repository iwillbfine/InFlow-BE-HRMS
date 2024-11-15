package com.pado.inflow.attendance.command.domain.repository;

import com.pado.inflow.attendance.command.domain.aggregate.entity.LeaveReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveReturnRepository extends JpaRepository<LeaveReturn, Long> {
    Optional<LeaveReturn> findByAttendanceRequestId(Long attendanceRequestId);
}
