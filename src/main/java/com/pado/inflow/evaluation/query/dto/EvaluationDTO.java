package com.pado.inflow.evaluation.query.dto;

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
    private Long evaluationId;
    private String evaluationType;
    private String finGrade;
    private Double finScore;
    private Integer year;
    private String half;
    private LocalDateTime createdAt;
    private Long evaluatorId;
    private Long employeeId;
}
