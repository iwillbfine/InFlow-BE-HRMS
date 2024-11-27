package com.pado.inflow.evaluation.command.domain.aggregate.entity;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskEvalResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "task_eval")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEvalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_eval_id")
    private Long taskEvalId;

    @Column(name = "task_eval_name", nullable = false)
    private String taskEvalName;

    @Column(name = "task_eval_content", nullable = false)
    private String taskEvalContent;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "set_ratio", nullable = false)
    private Double setRatio;

    @Column(name = "task_grade")
    private String taskGrade;

    @Column(name = "performance_input", nullable = false)
    private String performanceInput;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "rel_eval_status", nullable = false)
    private Boolean relEvalStatus;

    @Column(name = "evaluation_id", nullable = false)
    private Long evaluationId;

    @Column(name = "modifiable_date", nullable = false)
    private LocalDateTime modifiableDate;

    @Column(name = "task_type_id", nullable = false)
    private Long taskTypeId;

    @Column(name = "task_item_id", nullable = false)
    private Long taskItemId;

    // TaskEvalEntity를 TaskEvalResponseDTO로 변환하는 메소드
    public TaskEvalResponseDTO toResponseDTO() {
        return TaskEvalResponseDTO.builder()
                .taskEvalId(this.taskEvalId)
                .taskEvalName(this.taskEvalName)
                .taskEvalContent(this.taskEvalContent)
                .score(this.score)
                .setRatio(this.setRatio)
                .taskGrade(this.taskGrade)
                .performanceInput(this.performanceInput)
                .createdAt(this.createdAt)
                .relEvalStatus(this.relEvalStatus)
                .evaluationId(this.evaluationId)
                .modifiableDate(this.modifiableDate)
                .taskTypeId(this.taskTypeId)
                .taskItemId(this.taskItemId)
                .build();
    }
}
