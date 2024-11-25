package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TotalWorkingDaysDTO {

    @JsonProperty("employee_id")
    private Long employeeId;

    @JsonProperty("join_date")
    private LocalDate joinDate;

    @JsonProperty("severance_date")
    private LocalDate severanceDate;

    @JsonProperty("total_working_days")
    private Integer totalWorkingDays;
}
