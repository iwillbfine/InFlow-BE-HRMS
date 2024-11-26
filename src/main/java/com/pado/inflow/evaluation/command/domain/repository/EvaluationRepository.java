package com.pado.inflow.evaluation.command.domain.repository;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository <EvaluationEntity, Long> {
    List<EvaluationEntity> findByYearAndHalfOrderByFinScoreDesc(Integer year, String half);
}
