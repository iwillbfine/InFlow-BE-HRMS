package com.pado.inflow.statistics.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MonthsOA {
    private int month;
    private int monthlyEmployeeCount;
    private Long monthlyTotalAmount;
}