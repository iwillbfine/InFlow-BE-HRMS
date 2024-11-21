package com.pado.inflow.department.query.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetDepartmentMemberDTO {

    // 사원명, 사원코드, 부서명 의 키워드 입력을 통한 사원 목록 조회 DTO

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("department_path")
    private String departmentPath;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("role_name")
    private String roleName;

}
