package com.pado.inflow.evaluation.command.domain.repository;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Optional 반환형 지정 안하면 형변환 해야하니 주의 !
@Repository
public interface TaskTypeRepository extends JpaRepository<TaskTypeEntity, Long> {
    Optional<TaskTypeEntity> findByTaskTypeName(String taskTypeName);

    Optional<TaskTypeEntity> findByTaskTypeId(Long taskTypeId);

    // 특정 TaskType에 연결된 모든 EvaluationPolicy를 반환
    @Query("SELECT e FROM EvaluationPolicyEntity e WHERE e.taskType.taskTypeId = :taskTypeId")
    List<EvaluationPolicyEntity> findEvaluationsPoliciesByTaskTypeId(Long taskTypeId);

}