package com.pado.inflow.department.query.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetDepartmentDetailDTO {

    // 부서 상세 조회 -> 부서 정보

    // 부서명
    @JsonProperty("department_name")
    private String departmentName;

    // 부서코드
    @JsonProperty("department_code")
    private String departmentCode;

    // 상위 부서명
    @JsonProperty("upper_department_name")
    private String upperDepartmentName;


    // 최소 인원수
    @JsonProperty("min_employee_num")
    private String minEmployeeNum;

    // 부서장 -> 부서테이블, 부서구성원 테이블을 부서코드 기준으로 조인 -> role_name이 부서장인 행의 name 가져오기
    @JsonProperty("department_head_name")
    private String departmentHeadName;




}
