package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEvaluationPolicyRequestDTO {


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

    @JsonProperty("modifiable_date")
    private LocalDateTime modifiableDate;

    @JsonProperty("policy_description")
    private String policyDescription;

    // Entity 업데이트 메서드
    public EvaluationPolicyEntity toEntity() {
        return EvaluationPolicyEntity.builder()
                .startDate(this.startDate)
                .endDate(this.endDate)
                .year(this.year)
                .half(this.half)
                .taskRatio(this.taskRatio)
                .minRelEvalCount(this.minRelEvalCount)
                .modifiableDate(this.modifiableDate)
                .policyDescription(this.policyDescription)
                .build();
    }
}
