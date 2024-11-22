package com.pado.inflow.evaluation.command.domain.aggregate.entity;


import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateFeedbackResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateFeedbackResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "evaluation_id", nullable = false)
    private Long evaluationId;

    // 생성 시점 자동화를 위한 PrePersist
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // 업데이트 메서드
    public void updateContent(String content) {
        this.content = content;
    }

    // Entity -> ResponseDTO 변환 메서드
    public static CreateFeedbackResponseDTO toDTO(FeedbackEntity entity) {
        return CreateFeedbackResponseDTO.builder()
                .feedbackId(entity.getFeedbackId())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .evaluationId(entity.getEvaluationId())
                .build();
    }

    // Update ResponseDTO 변환
    public static UpdateFeedbackResponseDTO toUpdateDTO(FeedbackEntity entity) {
        return UpdateFeedbackResponseDTO.builder()
                .feedbackId(entity.getFeedbackId())
                .content(entity.getContent())
                .evaluationId(entity.getEvaluationId())
                .build();
    }


}