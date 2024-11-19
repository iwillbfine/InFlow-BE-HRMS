package com.pado.inflow.evaluation.command.domain.aggregate.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
