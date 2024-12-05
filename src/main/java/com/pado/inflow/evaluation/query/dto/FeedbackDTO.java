package com.pado.inflow.evaluation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.FeedbackEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackDTO {

    @JsonProperty("feedback_id")
    private Long feedbackId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("evaluation_id")
    private Long evaluationId;

    // Entity 변환 메서드
    public FeedbackEntity toEntity() {
        return FeedbackEntity.builder()
                .feedbackId(this.feedbackId)
                .content(this.content)
                .evaluationId(this.evaluationId)
                .createdAt(this.createdAt)
                .build();
    }
}