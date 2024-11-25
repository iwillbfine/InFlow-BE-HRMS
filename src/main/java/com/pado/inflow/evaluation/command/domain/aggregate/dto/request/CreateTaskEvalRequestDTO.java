package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskEvalRequestDTO {

//    @JsonProperty("task_eval_id")
//    private Long taskEvalId;

    @JsonProperty("task_eval_name")
    private String taskEvalName;

    @JsonProperty("task_eval_content")
    private String taskEvalContent;

    @JsonProperty("score")
    private Double score;

    @JsonProperty("set_ratio")
    private Double setRatio;

//    @JsonProperty("task_grade")
//    private String taskGrade;

    @JsonProperty("performance_input")
    private String performanceInput;

//    @JsonProperty("created_at")
//    private LocalDateTime createdAt;

//    @JsonProperty("rel_eval_status")
//    private Boolean relEvalStatus;

    @JsonProperty("evaluation_id")
    private Long evaluationId;

//    @JsonProperty("modifiable_date")
//    private LocalDateTime modifiableDate;

    @JsonProperty("task_type_id")
    private Long taskTypeId;

    @JsonProperty("task_item_id")
    private Long taskItemId;
}