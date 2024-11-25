package com.pado.inflow.evaluation.command.domain.aggregate.dto.response;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.GradeEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponseDTO {
    private Long gradeId;
    private String gradeName;
    private Double startRatio;
    private Double endRatio;
    private Double absoluteGradeRatio;
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