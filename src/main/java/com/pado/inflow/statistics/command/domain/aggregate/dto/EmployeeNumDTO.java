package com.pado.inflow.statistics.command.domain.aggregate.dto;

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
public class EmployeeNumDTO {
    private Long statisticsId;
    private int year;
    private int month;
    private String half;
    private Long totalEmployeeNum;
    private Long joinedEmployeeNum;
    private Long leftedEmployeeNum;
    private LocalDate createdAt;

    public EmployeeNumDTO(int year, int month, String half, Long totalEmployeeNum, Long joinedEmployeeNum, Long leftedEmployeeNum, LocalDate createdAt) {
        this.year = year;
        this.month = month;
        this.half = half;
        this.totalEmployeeNum = totalEmployeeNum;
        this.joinedEmployeeNum = joinedEmployeeNum;
        this.leftedEmployeeNum = leftedEmployeeNum;
        this.createdAt = createdAt;
    }
}
