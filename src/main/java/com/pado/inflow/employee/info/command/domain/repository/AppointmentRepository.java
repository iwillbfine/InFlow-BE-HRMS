package com.pado.inflow.employee.info.command.domain.repository;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Appointment;
import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}