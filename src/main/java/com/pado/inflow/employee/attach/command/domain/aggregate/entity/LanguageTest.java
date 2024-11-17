package com.pado.inflow.employee.attach.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "LanguageTest")
@Table(name = "language_test")
public class LanguageTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_test_id")
    private Long languageTestId;

    @Column(name = "language_test_name")
    private String languageTestName;

    @Column(name = "qualification_number")
    private String qualificationNumber;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "qualified_at")
    private LocalDate qualifiedAt;

    @Column(name = "grade_score")
    private String gradeScore;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "language_code")
    private String languageCode;
}





