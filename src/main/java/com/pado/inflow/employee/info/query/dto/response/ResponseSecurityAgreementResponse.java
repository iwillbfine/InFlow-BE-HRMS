package com.pado.inflow.employee.info.query.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseSecurityAgreementResponse {
    @JsonProperty("employee_name")
    private String employeeName; // 직원 이름

    @JsonProperty("company_name")
    private String companyName; // 회사명

    @JsonProperty("corporate_registration_number")
    private String corporateRegistrationNumber; // 법인등록번호

    @JsonProperty("company_address")
    private String companyAddress; // 회사 주소

    @JsonProperty("agreement_date")
    private String agreementDate; // 서약 날짜 (현재 시각)
}
