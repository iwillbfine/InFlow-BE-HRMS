package com.pado.inflow.employee.attach.command.domain.aggregate.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QualificationDTO {
    private Long qualificationId;
    private String qualificationName;
    private String qualificationNumber;
    private LocalDate qualifiedAt;
    private String issuer;
    private String gradeScore;
    private Long employeeId;

    public QualificationDTO(String qualificationName, String qualificationNumber, LocalDate qualifiedAt, String issuer, String gradeScore, Long employeeId) {
        this.qualificationName = qualificationName;
        this.qualificationNumber = qualificationNumber;
        this.qualifiedAt = qualifiedAt;
        this.issuer = issuer;
        this.gradeScore = gradeScore;
        this.employeeId = employeeId;
    }
}
