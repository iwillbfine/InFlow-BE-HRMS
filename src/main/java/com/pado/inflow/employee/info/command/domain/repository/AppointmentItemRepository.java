package com.pado.inflow.employee.info.command.domain.repository;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.AppointmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentItemRepository extends JpaRepository<AppointmentItem, String> {
}
