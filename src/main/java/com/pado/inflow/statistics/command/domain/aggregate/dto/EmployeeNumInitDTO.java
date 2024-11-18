package com.pado.inflow.statistics.command.domain.aggregate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeNumInitDTO {
    private Long employeeId;
    private int inYear;
    private int inMonth;
    private String left;
    private Integer leftYear;
    private Integer leftMonth;
}
