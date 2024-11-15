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
public class EducationDTO {
    private Long educationId;
    private String schoolName;
    private LocalDate admissionDate;
    private LocalDate graduationDate;
    private String degree;
    private String major;
    private Long employeeId;

    public EducationDTO(String schoolName,
                        LocalDate admissionDate,
                        LocalDate graduationDate,
                        String degree,
                        String major,
                        Long employeeId) {
        this.schoolName = schoolName;
        this.admissionDate = admissionDate;
        this.graduationDate = graduationDate;
        this.degree = degree;
        this.major = major;
        this.employeeId = employeeId;
    }
}
