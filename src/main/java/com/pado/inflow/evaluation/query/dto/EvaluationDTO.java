package com.pado.inflow.evaluation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {

    @JsonProperty("evaluation_id")
    private Long evaluationId;

    @JsonProperty("evaluation_type")
    private String evaluationType;

    @JsonProperty("fin_grade")
    private String finGrade;

    @JsonProperty("fin_score")
    private Double finScore;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("half")
    private String half;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("evaluator_id")
    private Long evaluatorId;

    @JsonProperty("employee_id")
    private Long employeeId;
}
