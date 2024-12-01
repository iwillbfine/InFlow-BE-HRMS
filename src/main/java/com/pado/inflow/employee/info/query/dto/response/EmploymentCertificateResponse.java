package com.pado.inflow.employee.info.query.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmploymentCertificateResponse {
    //설명. 사원 정보
    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("birth_date")
    private String birthDate;

    @JsonProperty("address")
    private String address;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("position_name")
    private String positionName;

    @JsonProperty("purpose")
    private String purpose;

    @JsonProperty("join_date")
    private String joinDate; // 입사일 추가

    //설명. 회사 정보
    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("company_address")
    private String companyAddress;

    @JsonProperty("ceo_name")
    private String ceoName;

    @JsonProperty("business_registration_number")
    private String businessRegistrationNumber;

    // 추가 항목
    @JsonProperty("issue_date")
    private String issueDate;

    @JsonProperty("content")
    private String content;

    @JsonProperty("company_stamp_url")
    private String companyStampUrl;
}
