package com.pado.inflow.department.query.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetMemberDetailDTO {

    // 사원 검색 상세조회에서 보여줄 정보
    // 사원명, 직책, 상위/하위 부서, 사내 메일, 휴대번호

    private String employeeName;    // 사원명
    private String roleName;        // 직책
    private String departmentPath;  // 부서 경로
    private String profileImageUrl; // 사원 프로필 이미지
    private String employeeEmail;  // 사내 메일
    private String employeePhoneNumber;    // 사내 번호


}
