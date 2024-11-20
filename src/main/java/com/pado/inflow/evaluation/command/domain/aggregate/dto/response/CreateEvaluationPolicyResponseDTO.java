package com.pado.inflow.evaluation.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEvaluationPolicyResponseDTO {



    @JsonProperty("evaluation_policy_id")
    private Long evaluationPolicyId;

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

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("modifiable_date")
    private LocalDateTime modifiableDate;

    @JsonProperty("policy_description")
    private String policyDescription;

    @JsonProperty("policy_register_id")
    private Long policyRegisterId;

    @JsonProperty("task_type_id")
    private Long taskTypeId;


    /*
     Entity -> DTO 변환 메소드가 Static인 이유
     :
     static이 없다면 인스턴스를 먼저 생성하고
     ExampleDTO example = new ExampleDTO();
     "ExampleDTO.EntityToDTO(ExampleEntity);" 와 같이 사용해야하나
     static 메소드의 경우
     ExampleDTO example = ExanpleDTO.EntityToDTO(ExampleEntity)
     처럼 한 번에 수월하게 코딩이 가능하기 때문이다.
     */
    public static CreateEvaluationPolicyResponseDTO EntityToDTO(EvaluationPolicyEntity entity) {
        return CreateEvaluationPolicyResponseDTO.builder()
                .evaluationPolicyId(entity.getEvaluationPolicyId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .year(entity.getYear())
                .half(entity.getHalf())
                .taskRatio(entity.getTaskRatio())
                .minRelEvalCount(entity.getMinRelEvalCount())
                .createdAt(entity.getCreatedAt())
                .modifiableDate(entity.getModifiableDate())
                .policyDescription(entity.getPolicyDescription())
                .policyRegisterId(entity.getPolicyRegisterId())
                .taskTypeId(entity.getTaskTypeId())
                .build();
    }
}
