package com.pado.inflow.evaluation.query.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data // 테스트용
public class GradeDTO {

    private Long gradeId;
    private String gradeName;
    private Double startRatio;
    private Double endRatio;
    private Double absoluteGradeRatio;
    private Long evaluationPolicyId;
}