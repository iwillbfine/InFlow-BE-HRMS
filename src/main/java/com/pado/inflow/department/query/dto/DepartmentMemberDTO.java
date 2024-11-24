package com.pado.inflow.department.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data // 테스트용
public class DepartmentMemberDTO {

    private Long departmentMemberId;
    private String employeeNumber;
    private String name;
    private String roleName;
    private String email;
    private String profileImgUrl;
    private String phoneNumber;
    private String attendanceStatusTypeName;
    private String managerStatus;
    private String departmentCode;
    private Long employeeId;
}