package com.pado.inflow.evaluation.query.dto;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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

    // 평가 정책 수정 시 사용됨.
    public EvaluationPolicyEntity toEntity() {
        return EvaluationPolicyEntity.builder()
                .evaluationPolicyId(this.evaluationPolicyId)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .year(this.year)
                .half(this.half)
                .taskRatio(this.taskRatio)
                .minRelEvalCount(this.minRelEvalCount)
                .modifiableDate(this.modifiableDate)
                .policyDescription(this.policyDescription)
                .policyRegisterId(this.policyRegisterId)
                .taskTypeId(this.taskTypeId)
                .build();
    }
}
