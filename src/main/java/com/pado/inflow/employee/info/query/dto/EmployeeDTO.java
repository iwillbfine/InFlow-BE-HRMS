package com.pado.inflow.employee.info.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.employee.info.enums.EmployeeRole;
import com.pado.inflow.employee.info.enums.Gender;
import com.pado.inflow.employee.info.enums.JoinType;
import com.pado.inflow.employee.info.enums.ResignationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeDTO {

    @JsonProperty("employee_id")
    private Long employeeId;

    @JsonProperty("employee_number")
    private String employeeNumber;

    @JsonProperty("employee_role")
    private EmployeeRole employeeRole;

    @JsonProperty("password")
    private String password;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_date")
    private LocalDateTime birthDate;

    @JsonProperty("resident_registration_number")
    private String residentRegistrationNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("profile_img_url")
    private String profileImgUrl;

    @JsonProperty("join_date")
    private LocalDateTime joinDate;

    @JsonProperty("join_type")
    private JoinType joinType;

    @JsonProperty("resignation_date")
    private LocalDateTime resignationDate;

    @JsonProperty("resignation_status")
    private ResignationStatus resignationStatus;

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

    @JsonProperty("department_code")
    private String departmentCode;

    @JsonProperty("attendance_status_type_code")
    private String attendanceStatusTypeCode;

    @JsonProperty("position_code")
    private String positionCode;

    @JsonProperty("role_code")
    private String roleCode;

    @JsonProperty("duty_code")
    private String dutyCode;
}
