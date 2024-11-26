package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEvalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskTypeEvalRequestDTO {

    @JsonProperty("task_type_eval_id")
    private Long taskTypeEvalId; // task_type_eval_id

    @JsonProperty("task_type_total_score")
    private Double taskTypeTotalScore; // task_type_total_score

    @JsonProperty("created_at")
    private LocalDateTime createdAt; // created_at

    @JsonProperty("evaluation_id")
    private Long evaluationId; // evaluation_id

    @JsonProperty("evaluation_policy_id")
    private Long evaluationPolicyId; // evaluation_policy_id

    // DTO -> Entity 변환 메서드
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