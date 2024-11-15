package com.pado.inflow.attendance.command.domain.repository;

import com.pado.inflow.attendance.command.domain.aggregate.entity.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommuteRepository extends JpaRepository<Commute, Long> {
    Optional<Commute> findByAttendanceRequestId(Long attendanceRequestId);
}
