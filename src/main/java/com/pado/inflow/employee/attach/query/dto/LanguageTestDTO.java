package com.pado.inflow.employee.attach.query.dto;

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
    private String languageName;
}
