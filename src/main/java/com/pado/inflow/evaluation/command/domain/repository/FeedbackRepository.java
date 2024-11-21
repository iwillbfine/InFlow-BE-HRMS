package com.pado.inflow.evaluation.command.domain.repository;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
}
