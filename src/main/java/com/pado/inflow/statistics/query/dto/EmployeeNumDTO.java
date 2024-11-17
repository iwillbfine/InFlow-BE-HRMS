package com.pado.inflow.statistics.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeNumDTO {
    private int year;
    private int month;
    private String half;
    private Long totalEmployeeNum;
    private Long joinedEmployeeNum;
    private Long leftedEmployeeNum;
}
