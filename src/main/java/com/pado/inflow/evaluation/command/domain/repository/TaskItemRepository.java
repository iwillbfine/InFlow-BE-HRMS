package com.pado.inflow.evaluation.command.domain.repository;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CommandTaskItemRepository")
public interface TaskItemRepository extends JpaRepository<TaskItemEntity, Long> {
}
