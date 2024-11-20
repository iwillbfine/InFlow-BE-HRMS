package com.pado.inflow.evaluation.command.domain.repository;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationPolicyRepository extends JpaRepository<EvaluationPolicyEntity, Long> {


    List<EvaluationPolicyEntity> findEvaluationPolicyByYearAndHalf(Integer year, String half);
}
