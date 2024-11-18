package com.pado.inflow.employee.info.command.domain.repository;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DutyRepository extends JpaRepository<Duty, String> {
    Optional<String> findDutyNameByDutyCode(String dutyCode);
}