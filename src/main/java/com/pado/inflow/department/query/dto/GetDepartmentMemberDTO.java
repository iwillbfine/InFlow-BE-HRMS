package com.pado.inflow.department.query.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetDepartmentMemberDTO {

    // 사원명, 사원코드, 부서명 의 키워드 입력을 통한 사원 목록 조회 DTO

    private String departmentName;
    private String departmentPath;
    private String employeeName;
    private String profileImageUrl;
    private String roleName;

}
