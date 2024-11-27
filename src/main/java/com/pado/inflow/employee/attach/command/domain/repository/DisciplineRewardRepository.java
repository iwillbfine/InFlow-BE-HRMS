package com.pado.inflow.employee.attach.command.domain.repository;

import com.pado.inflow.employee.attach.command.domain.aggregate.entity.DisciplineReward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRewardRepository extends JpaRepository<DisciplineReward, Long> {
}
