package com.pado.inflow.department.query.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentHierarchyDTO {

    @JsonProperty("department_code")
    private String departmentCode;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("full_path")
    private String fullPath;

    @JsonProperty("upper_department_code")
    private String upperDepartmentCode;

    // 계층 구조를 위한 subDepartments 필드 추가
    @JsonProperty("sub_departments")
    @Builder.Default // 기본값으로 빈 리스트 설정
    private List<DepartmentHierarchyDTO> subDepartments = new ArrayList<>();


}
