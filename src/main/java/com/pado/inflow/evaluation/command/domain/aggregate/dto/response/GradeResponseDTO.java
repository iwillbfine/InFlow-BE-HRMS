package com.pado.inflow.evaluation.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.GradeEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponseDTO {

    @JsonProperty("grade_id")
    private Long gradeId;

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

    public static GradeResponseDTO from(GradeEntity grade) {
        return GradeResponseDTO.builder()
                .gradeId(grade.getGradeId())
                .gradeName(grade.getGradeName())
                .startRatio(grade.getStartRatio())
                .endRatio(grade.getEndRatio())
                .absoluteGradeRatio(grade.getAbsoluteGradeRatio())
                .evaluationPolicyId(grade.getEvaluationPolicyId())
                .build();
    }
}