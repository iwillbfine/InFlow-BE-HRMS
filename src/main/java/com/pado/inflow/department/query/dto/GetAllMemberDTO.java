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
public class GetAllMemberDTO {
    // 설명. 추가: 전체 사원 조회

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("department_path")
    private String departmentPath;

    @JsonProperty("department_member_id")
    private String departmentMemberId;

    @JsonProperty("employee_number")
    private String employeeCode;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("role_name")
    private String roleName;

}
