package com.pado.inflow.employee.attach.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "Qualification")
@Table(name = "qualification")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private Long qualificationId;

    @Column(name = "qualification_name")
    private String qualificationName;

    @Column(name = "qualification_number")
    private String qualificationNumber;

    @Column(name = "qualified_at")
    private LocalDate qualifiedAt;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "grade_score")
    private String gradeScore;

    @Column(name = "employee_id")
    private Long employeeId;
}
