package com.pado.inflow.evaluation.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEvaluationPolicyResponseDTO {



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


    public static CreateEvaluationPolicyResponseDTO EntityToDTO(EvaluationPolicyEntity entity) {
        return CreateEvaluationPolicyResponseDTO.builder()
                .evaluationPolicyId(entity.getEvaluationPolicyId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .year(entity.getYear())
                .half(entity.getHalf())
                .taskRatio(entity.getTaskRatio())
                .minRelEvalCount(entity.getMinRelEvalCount())
                .createdAt(entity.getCreatedAt())
                .modifiableDate(entity.getModifiableDate())
                .policyDescription(entity.getPolicyDescription())
                .policyRegisterId(entity.getPolicyRegisterId())
                .taskTypeId(entity.getTaskTypeId())
                .build();
    }
}
