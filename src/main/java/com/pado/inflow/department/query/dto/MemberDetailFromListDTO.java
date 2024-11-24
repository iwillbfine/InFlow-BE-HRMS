package com.pado.inflow.department.query.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDetailFromListDTO {

    // 사원찾기
    // 사원 검색 상세조회에서 보여줄 정보
    // 사원명, 직책, 상위/하위 부서, 사내 메일, 휴대번호

    @JsonProperty("employee_name")
    private String employeeName;    // 사원명

    @JsonProperty("role_name")
    private String roleName;        // 직책

    @JsonProperty("department_path")
    private String departmentPath;  // 부서 경로

    @JsonProperty("profile_img_url")
    private String profileImageUrl; // 사원 프로필 이미지

    @JsonProperty("employee_email")
    private String employeeEmail;  // 사내 메일

    @JsonProperty("employee_phone_number")
    private String employeePhoneNumber;    // 사내 번호

    @JsonProperty("attendance_status_type_name")
    private String attendanceStatusTypeName;    // 근태 상태


}
