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
public class ManagerDepartmentMemberListDTO {

    // 사원명, 사원 번호, 사원 부서경로, 사원코드, 근태상태,

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("department_member_id")
    private Long departmentMemberId;

    @JsonProperty("employee_number")
    private String employeeNumber;

    @JsonProperty("department_path")
    private String departmentPath;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("attendance_status_type_name")
    private String attendanceStatusTypeName;



}
