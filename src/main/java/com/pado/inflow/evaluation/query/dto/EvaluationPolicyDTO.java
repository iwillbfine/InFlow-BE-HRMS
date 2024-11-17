package com.pado.inflow.evaluation.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EvaluationPolicyDTO {

    private Long evaluationPolicyId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer year;
    private String half;
    private Double taskRatio;
    private Long minRelEvalCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiableDate;
    private String policyDescription;
    private Long policyRegisterId;
    private Long taskTypeId;

}
