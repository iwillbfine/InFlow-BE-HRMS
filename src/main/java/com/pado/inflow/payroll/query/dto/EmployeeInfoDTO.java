package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoDTO {

    @JsonProperty("annual_bonus")
    private int annualBonus; // 소수점 버림으로 인해 int 사용

    @JsonProperty("unused_leave_days")
    private int unusedLeaveDays;

    @JsonProperty("unused_leave_pay_per_day")
    private int unusedLeavePayPerDay; // 소수점 버림으로 인해 int 사용

    @JsonProperty("monthly_salary")
    private int monthlySalary; // 소수점 버림으로 인해 int 사용

}

