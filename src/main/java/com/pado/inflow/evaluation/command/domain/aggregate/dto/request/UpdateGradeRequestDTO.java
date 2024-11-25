package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UpdateGradeRequestDTO {
    @JsonProperty("grade_name")
    private String gradeName;

    @JsonProperty("start_ratio")
    private Double startRatio;

    @JsonProperty("end_ratio")
    private Double endRatio;

    @JsonProperty("absolute_grade_ratio")
    private Double absoluteGradeRatio;
}