package com.pado.inflow.employee.info.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseContractDTO {

    @JsonProperty("contract_id")
    private Long contractId; // 계약서 ID

    @JsonProperty("contract_type")
    private String contractType; // 계약서 종류

    @JsonProperty("file_name")
    private String fileName; // 계약서 파일명

    @JsonProperty("file_url")
    private String fileUrl; // 계약서 파일 URL

    @JsonProperty("contract_status")
    private String contractStatus; // 계약서 상태 (SIGNING, PENDING, APPROVED, REJECTED)

    @JsonProperty("consent_status")
    private String consentStatus; // 동의 여부

    @JsonProperty("created_at")
    private String createdAt; // 등록 시각 (ISO-8601 포맷 권장)

    @JsonProperty("employee_id")
    private Long employeeId; // 사원 ID
}
