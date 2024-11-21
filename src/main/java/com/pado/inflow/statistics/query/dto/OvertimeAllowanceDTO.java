package com.pado.inflow.statistics.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OvertimeAllowanceDTO {
    private int year;
    private int yearlyEmployeeCount;
    private Long yearlyTotalAmount;
    private List<MonthlyOvertimeAllowance> monthly;
}

