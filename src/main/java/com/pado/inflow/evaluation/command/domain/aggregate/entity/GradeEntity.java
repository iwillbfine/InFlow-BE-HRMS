package com.pado.inflow.evaluation.command.domain.aggregate.entity;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.GradeResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    @Column(name = "grade_name", nullable = false)
    private String gradeName;

    @Column(name = "start_ratio", nullable = false)
    private Double startRatio;

    @Column(name = "end_ratio", nullable = false)
    private Double endRatio;

    @Column(name = "absolute_grade_ratio", nullable = false)
    private Double absoluteGradeRatio;

    @Column(name = "evaluation_policy_id", nullable = false)
    private Long evaluationPolicyId;

    public GradeResponseDTO toResponseDTO() {
        return GradeResponseDTO.builder()
                .gradeId(this.gradeId)
                .gradeName(this.gradeName)
                .startRatio(this.startRatio)
                .endRatio(this.endRatio)
                .absoluteGradeRatio(this.absoluteGradeRatio)
                .evaluationPolicyId(this.evaluationPolicyId)
                .build();
    }
}