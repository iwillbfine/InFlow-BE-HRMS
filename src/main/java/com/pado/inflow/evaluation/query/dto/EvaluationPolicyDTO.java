package com.pado.inflow.evaluation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EvaluationPolicyDTO {

    @JsonProperty("evaluation_policy_id")
    private Long evaluationPolicyId;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("half")
    private String half;

    @JsonProperty("task_ratio")
    private Double taskRatio;

    @JsonProperty("min_rel_eval_count")
    private Long minRelEvalCount;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("modifiable_date")
    private LocalDateTime modifiableDate;

    @JsonProperty("policy_description")
    private String policyDescription;

    @JsonProperty("policy_register_id")
    private Long policyRegisterId;

    @JsonProperty("task_type_id")
    private Long taskTypeId;

    // 평가 정책 수정 시 사용됨.
    public EvaluationPolicyEntity toEntity() {
        return EvaluationPolicyEntity.builder()
                .evaluationPolicyId(this.evaluationPolicyId)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .year(this.year)
                .half(this.half)
                .taskRatio(this.taskRatio)
                .minRelEvalCount(this.minRelEvalCount)
                .modifiableDate(this.modifiableDate)
                .policyDescription(this.policyDescription)
                .policyRegisterId(this.policyRegisterId)
                .taskTypeId(this.taskTypeId)
                .build();
    }
}
