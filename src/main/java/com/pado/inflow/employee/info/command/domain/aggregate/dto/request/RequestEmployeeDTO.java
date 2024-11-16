package com.pado.inflow.employee.info.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.employee.info.enums.EmployeeRole;
import com.pado.inflow.employee.info.enums.Gender;
import com.pado.inflow.employee.info.enums.ResignationStatus;
import com.pado.inflow.employee.info.enums.JoinType;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
public class RequestEmployeeDTO {

    @NotBlank
    @JsonProperty("employee_number") // 사원 번호
    private String employeeNumber;

    @NotNull
    @JsonProperty("employee_role") // 사원 역할 (ENUM)
    private EmployeeRole employeeRole;

    @NotBlank
    @JsonProperty("password") // 비밀번호
    private String password;

    @NotNull
    @JsonProperty("gender") // 성별 (ENUM)
    private Gender gender;

    @NotBlank
    @JsonProperty("name") // 이름
    private String name;

    @NotNull
    @JsonProperty("birth_date") // 생년월일
    private LocalDateTime birthDate;

    @NotBlank
    @JsonProperty("resident_registration_number") // 주민등록번호
    private String residentRegistrationNumber;

    @NotBlank
    @Email
    @JsonProperty("email") // 이메일
    private String email;

    @NotBlank
    @JsonProperty("phone_number") // 전화번호
    private String phoneNumber;

    @NotBlank
    @JsonProperty("profile_img_url") // 프로필 이미지 URL
    private String profileImgUrl;

    @NotNull
    @JsonProperty("join_date") // 입사일
    private LocalDateTime joinDate;

    @NotNull
    @JsonProperty("join_type") // 입사 유형 (ENUM)
    private JoinType joinType;

    @JsonProperty("resignation_date") // 퇴사일
    private LocalDateTime resignationDate;

    @NotNull
    @JsonProperty("resignation_status") // 퇴사 상태 (ENUM)
    private ResignationStatus resignationStatus;

    @NotNull
    @JsonProperty("salary") // 연봉
    private Long salary;

    @NotNull
    @JsonProperty("monthly_salary") // 월급
    private Long monthlySalary;

    @NotBlank
    @JsonProperty("street_address") // 거리 주소
    private String streetAddress;

    @NotBlank
    @JsonProperty("detailed_address") // 상세 주소
    private String detailedAddress;

    @NotBlank
    @JsonProperty("postcode") // 우편번호
    private String postcode;

    @NotBlank
    @JsonProperty("department_code") // 부서 코드
    private String departmentCode;

    @NotBlank
    @JsonProperty("attendance_status_type_code") // 근태 상태 코드
    private String attendanceStatusTypeCode;

    @NotBlank
    @JsonProperty("position_code") // 직위 코드
    private String positionCode;

    @NotBlank
    @JsonProperty("role_code") // 역할 코드
    private String roleCode;

    @NotBlank
    @JsonProperty("duty_code") // 직무 코드
    private String dutyCode;
}
