package com.pado.inflow.employee.attach.command.domain.aggregate.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LanguageTestDTO {
    private Long languageTestId;
    private String languageTestName;
    private String qualificationNumber;
    private String issuer;
    private LocalDate qualifiedAt;
    private String gradeScore;
    private Long employeeId;
    private String languageCode;

    public LanguageTestDTO(String languageTestName, String qualificationNumber, String issuer, LocalDate qualifiedAt, String gradeScore, Long employeeId, String languageCode) {
        this.languageTestName = languageTestName;
        this.qualificationNumber = qualificationNumber;
        this.issuer = issuer;
        this.qualifiedAt = qualifiedAt;
        this.gradeScore = gradeScore;
        this.employeeId = employeeId;
        this.languageCode = languageCode;
    }
}
