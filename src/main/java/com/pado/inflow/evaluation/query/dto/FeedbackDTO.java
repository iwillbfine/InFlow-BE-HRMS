package com.pado.inflow.evaluation.query.dto;

import com.pado.inflow.evaluation.command.domain.aggregate.entity.FeedbackEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackDTO {

    private Long feedbackId;
    private String content;
    private LocalDateTime createdAt;
    private Long evaluationId;

    // Entity 변환 메서드
    public FeedbackEntity toEntity() {
        return FeedbackEntity.builder()
                .feedbackId(this.feedbackId)
                .content(this.content)
                .evaluationId(this.evaluationId)
                .build();
    }
}