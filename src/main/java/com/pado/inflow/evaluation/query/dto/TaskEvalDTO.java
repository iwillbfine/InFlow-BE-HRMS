package com.pado.inflow.evaluation.query.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEvalDTO {
    private Long taskEvalId;
    private String taskEvalName;
    private String taskEvalContent;
    private Double score;
    private Double setRatio;
    private String taskGrade;
    private String performanceInput;
    private LocalDateTime createdAt;
    private Boolean relEvalStatus;
    private Long evaluationId;
    private LocalDateTime modifiableDate;
    private Long taskTypeId;
    private Long taskItemId;
}
