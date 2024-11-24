package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodicPayDTO {

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("days_in_period")
    private int daysInPeriod;

    @JsonProperty("basic_salary")
    private int basicSalary; // 소수점 버림으로 인해 int 사용

    @JsonProperty("allowance")
    private int allowance; // 소수점 버림으로 인해 int 사용
}
