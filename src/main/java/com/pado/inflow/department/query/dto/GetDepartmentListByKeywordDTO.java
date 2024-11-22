package com.pado.inflow.department.query.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDepartmentListByKeywordDTO {

    @JsonProperty("department_path")
    private String departmentPath;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("department_code")
    private String departmentCode;
}
