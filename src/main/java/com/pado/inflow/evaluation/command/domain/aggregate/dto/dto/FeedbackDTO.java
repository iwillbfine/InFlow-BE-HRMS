package com.pado.inflow.evaluation.command.domain.aggregate.dto.dto;

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
}