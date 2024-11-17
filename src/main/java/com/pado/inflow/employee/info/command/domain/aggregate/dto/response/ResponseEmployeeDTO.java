package com.pado.inflow.employee.info.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseEmployeeDTO {

    @JsonProperty("employee_id") // 사원 ID
    private Long employeeId;

    @JsonProperty("employee_number") // 사원 번호
    private String employeeNumber;

    @JsonProperty("name") // 이름
    private String name;

    @JsonProperty("email") // 이메일
    private String email;

    @JsonProperty("phone_number") // 전화번호
    private String phoneNumber;

    @JsonProperty("profile_img_url") // 프로필 이미지 URL
    private String profileImgUrl;

    @JsonProperty("department_code") // 부서 코드
    private String departmentCode;

    @JsonProperty("attendance_status_type_code") // 근태 상태 코드
    private String attendanceStatusTypeCode;

    @JsonProperty("position_code") // 직위 코드
    private String positionCode;

    @JsonProperty("role_code") // 역할 코드
    private String roleCode;

    @JsonProperty("duty_code") // 직무 코드
    private String dutyCode;

    @JsonProperty("salary") // 연봉
    private Long salary;

    @JsonProperty("monthly_salary") // 월급
    private Long monthlySalary;

    @JsonProperty("street_address") // 거리 주소
    private String streetAddress;

    @JsonProperty("detailed_address") // 상세 주소
    private String detailedAddress;

    @JsonProperty("postcode") // 우편번호
    private String postcode;

    @JsonProperty("join_date") // 입사일
    private String joinDate;

    @JsonProperty("resignation_date") // 퇴사일
    private String resignationDate;

    @JsonProperty("resignation_status") // 퇴사 상태
    private String resignationStatus;

    @JsonProperty("join_type") // 입사 유형
    private String joinType;

    @JsonProperty("gender") // 성별
    private String gender;
}
