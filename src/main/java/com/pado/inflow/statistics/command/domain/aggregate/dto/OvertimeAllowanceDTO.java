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
public class OvertimeAllowanceDTO {
    private Long statisticsId;
    private int year;
    private int month;
    private Long totalAmount;
    private LocalDate createdAt;
    private String departmentCode;

    public OvertimeAllowanceDTO(int year, int month, Long totalAmount, LocalDate createdAt, String departmentCode) {
        this.year = year;
        this.month = month;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.departmentCode = departmentCode;
    }
}
