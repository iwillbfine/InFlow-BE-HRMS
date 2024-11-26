package com.pado.inflow.evaluation.query.dto;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskEvalEntity;
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
