package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEvaluationRequestDTO {
    @JsonProperty("evaluation_type")
    private String evaluationType; // 평가 유형 (예: '자기 평가', '리더 평가' 등)

    @JsonProperty("fin_grade")
    private String finGrade;       // 최종 등급

    @JsonProperty("fin_score")
    private Double finScore;       // 최종 점수

    @JsonProperty("year")
    private Integer year;          // 평가 연도

    @JsonProperty("half")
    private String half;           // 반기 정보 ('1st', '2nd')

    @JsonProperty("created_at")
    private LocalDateTime createdAt; // 평가 생성 시각

    @JsonProperty("evaluator_id")
    private Long evaluatorId;      // 평가자 ID

    @JsonProperty("employee_id")
    private Long employeeId;       // 평가 대상자 ID

    // 엔티티로 변환하는 메소드
    public EvaluationEntity toEntity() {
        return EvaluationEntity.builder()
                .evaluationType(this.evaluationType)
                .finGrade(this.finGrade)
                .finScore(this.finScore)
                .year(this.year)
                .half(this.half)
                .createdAt(this.createdAt != null ? this.createdAt : LocalDateTime.now()) // 생성 시각이 null일 경우 현재 시각으로 설정
                .evaluatorId(this.evaluatorId)
                .employeeId(this.employeeId)
                .build();
    }
}
