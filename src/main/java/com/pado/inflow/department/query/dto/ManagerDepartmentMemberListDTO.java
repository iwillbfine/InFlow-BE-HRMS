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

    // 사원명, 사원 부서경로, 사원코드, 근태상태,

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("employee_number")
    private String employeeNumber;

    @JsonProperty("department_path")
    private String departmentPath;

    @JsonProperty("attendance_status_type_name")
    private String attendanceStatusTypeName;



}
