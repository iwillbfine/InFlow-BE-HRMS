package com.pado.inflow.evaluation.command.domain.aggregate.entity;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "evaluation_policy")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationPolicyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_policy_id")
    private Long evaluationPolicyId;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "half", nullable = false)
    private String half;

    @Column(name = "task_ratio", nullable = false)
    private Double taskRatio;

    @Column(name = "min_rel_eval_count", nullable = false)
    private Long minRelEvalCount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modifiable_date", nullable = false)
    private LocalDateTime modifiableDate;

    @Column(name = "policy_description", nullable = false, columnDefinition = "TEXT")
    private String policyDescription;

    @Column(name = "policy_register_id")
    private Long policyRegisterId;  // Entity 대신 ID만 저장

    @Column(name = "task_type_id")
    private Long taskTypeId;        // Entity 대신 ID만 저장
}