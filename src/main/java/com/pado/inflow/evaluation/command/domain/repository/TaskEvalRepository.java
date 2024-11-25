package com.pado.inflow.evaluation.command.domain.repository;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskEvalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskEvalRepository extends JpaRepository<TaskEvalEntity, Long> {
}
