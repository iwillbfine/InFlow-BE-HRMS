package com.pado.inflow.evaluation.command.domain.aggregate.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "evaluation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long evaluationId;

    @Column(name = "evaluation_type", nullable = false)
    private String evaluationType;

    @Column(name = "fin_grade")
    private String finGrade;

    @Column(name = "fin_score")
    private Double finScore;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "half", nullable = false)
    private String half;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "evaluator_id")
    private Long evaluatorId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

}
