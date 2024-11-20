package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEvaluationPolicyRequestDTO {

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("half")
    private String half;

    @JsonProperty("task_ratio")
    private Double taskRatio;

    @JsonProperty("min_rel_eval_count")
    private Long minRelEvalCount;

    @JsonProperty("modifiable_date")
    private LocalDateTime modifiableDate;

    @JsonProperty("policy_description")
    private String policyDescription;

    @JsonProperty("policy_register_id")
    private Long policyRegisterId;

    @JsonProperty("task_type_id")
    private Long taskTypeId;

    // 해당 방법으로도 setter 없이 설정 가능.
//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now().withNano(0);

    public EvaluationPolicyEntity toEntity() {
        return EvaluationPolicyEntity.builder()
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
                .createdAt(LocalDateTime.now().withNano(0))  // 현재 시간 자동 설정
                .build();
    }
}
