package com.pado.inflow.statistics.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DeptOvertimeAllowance {
    private String departmentCode;
    private String departmentName;
    private int employeeCount;
    private Long totalAmount;
}
