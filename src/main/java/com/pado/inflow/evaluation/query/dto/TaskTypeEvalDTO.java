package com.pado.inflow.evaluation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEvalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskTypeEvalDTO {

    @JsonProperty("task_type_eval_id")
    private Long taskTypeEvalId;

    @JsonProperty("task_type_total_score")
    private Double taskTypeTotalScore;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("evaluation_id")
    private Long evaluationId;

    @JsonProperty("evaluation_policy_id")
    private Long evaluationPolicyId;

    // Method to convert DTO to Entity
    public TaskTypeEvalEntity toEntity() {
        return TaskTypeEvalEntity.builder()
                .taskTypeEvalId(this.taskTypeEvalId)
                .taskTypeTotalScore(this.taskTypeTotalScore)
                .createdAt(this.createdAt)
                .evaluationId(this.evaluationId)
                .evaluationPolicyId(this.evaluationPolicyId)
                .build();
    }
}
