package com.pado.inflow.evaluation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data // 테스트용
public class GradeDTO {

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
}