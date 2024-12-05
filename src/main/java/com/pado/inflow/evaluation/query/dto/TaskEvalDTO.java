package com.pado.inflow.evaluation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskEvalEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEvalDTO {

    @JsonProperty("task_eval_id")
    private Long taskEvalId;

    @JsonProperty("task_eval_name")
    private String taskEvalName;

    @JsonProperty("task_eval_content")
    private String taskEvalContent;

    @JsonProperty("score")
    private Double score;

    @JsonProperty("set_ratio")
    private Double setRatio;

    @JsonProperty("task_grade")
    private String taskGrade;

    @JsonProperty("performance_input")
    private String performanceInput;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("rel_eval_status")
    private Boolean relEvalStatus;

    @JsonProperty("evaluation_id")
    private Long evaluationId;

    @JsonProperty("modifiable_date")
    private LocalDateTime modifiableDate;

    @JsonProperty("task_type_id")
    private Long taskTypeId;

    @JsonProperty("task_item_id")
    private Long taskItemId;

    // TaskEvalDTO를 TaskEvalEntity로 변환하는 메소드
    public TaskEvalEntity toEntity() {
        return TaskEvalEntity.builder()
                .taskEvalName(this.taskEvalName)
                .taskEvalContent(this.taskEvalContent)
                .score(this.score)
                .setRatio(this.setRatio)
                .performanceInput(this.performanceInput)
                .createdAt(LocalDateTime.now()) // 생성 시간은 현재 시간으로 설정
                .relEvalStatus(this.relEvalStatus)
                .evaluationId(this.evaluationId)
                .modifiableDate(this.modifiableDate)
                .taskTypeId(this.taskTypeId)
                .taskItemId(this.taskItemId)
                .build();
    }
}
