package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.GradeEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateGradeRequestDTO {

    @JsonProperty("grade_name")
    private String gradeName;

    @JsonProperty("start_ratio")
    private Double startRatio;

    @JsonProperty("end_ratio")
    private Double endRatio;

    @JsonProperty("absolute_grade_ratio")
    private Double absoluteGradeRatio;

    @JsonProperty("evaluation_policy_id")
    private Long evaluationPolicyId;

    // Entity 변환 메서드
    public GradeEntity toEntity() {
        return GradeEntity.builder()
                .gradeName(this.gradeName)
                .startRatio(this.startRatio)
                .endRatio(this.endRatio)
                .absoluteGradeRatio(this.absoluteGradeRatio)
                .evaluationPolicyId(this.evaluationPolicyId)
                .build();
    }
}