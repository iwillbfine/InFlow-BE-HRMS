package com.pado.inflow.employee.info.query.dto.response.validate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepartmentDTO {
    @JsonProperty("department_code")
    private String departmentCode;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("disbanded_at")
    private String disbandedAt;

    @JsonProperty("min_employee_num")
    private int minEmployeeNum;

    @JsonProperty("upper_department_code")
    private String upperDepartmentCode;
}
