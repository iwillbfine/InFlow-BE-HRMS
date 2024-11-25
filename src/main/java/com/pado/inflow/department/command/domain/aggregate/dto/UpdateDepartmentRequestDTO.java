package com.pado.inflow.department.command.domain.aggregate.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentRequestDTO {

    @JsonProperty("department_code")
    private String departmentCode;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("min_employee_num")
    private Integer minEmployeeNum;

    @JsonProperty("upper_department_code")
    private String upperDepartmentCode;

    @JsonProperty("department_head_name")
    private String departmentHeadName;

}
