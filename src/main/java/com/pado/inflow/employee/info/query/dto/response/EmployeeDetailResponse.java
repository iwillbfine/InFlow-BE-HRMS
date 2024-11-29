package com.pado.inflow.employee.info.query.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeDetailResponse {

    @JsonProperty("employee_id")
    private Long employeeId; // camelCase로 수정

    @JsonProperty("employee_number")
    private String employeeNumber;

    @JsonProperty("employee_role")
    private String employeeRole;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birth_date")
    private String birthDate;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("profile_img_url")
    private String profileImgUrl;

    @JsonProperty("join_date")
    private String joinDate;

    @JsonProperty("join_type")
    private String joinType;

    @JsonProperty("resignation_date")
    private String resignationDate;

    @JsonProperty("resignation_status")
    private String resignationStatus;

    @JsonProperty("salary")
    private Long salary;

    @JsonProperty("monthly_salary")
    private Long monthlySalary;

    @JsonProperty("street_address")
    private String streetAddress;

    @JsonProperty("detailed_address")
    private String detailedAddress;

    @JsonProperty("postcode")
    private String postcode;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("attendance_status_type_name")
    private String attendanceStatusTypeName;

    @JsonProperty("position_name")
    private String positionName;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("duty_name")
    private String dutyName;

}
