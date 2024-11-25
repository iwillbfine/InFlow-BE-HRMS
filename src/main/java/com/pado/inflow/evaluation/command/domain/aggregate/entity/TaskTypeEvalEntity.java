package com.pado.inflow.evaluation.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_type_eval")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskTypeEvalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_type_eval_id")
    private Long taskTypeEvalId;

    @Column(name = "task_type_total_score", nullable = false)
    private Double taskTypeTotalScore;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "evaluation_id", nullable = false)
    private Long evaluationId;

    @Column(name = "evaluation_policy_id", nullable = false)
    private Long evaluationPolicyId;
}
