package com.pado.inflow.employee.info.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseContractDTO {

    @JsonProperty("contract_id")
    private Long contractId; // 계약서 ID

    @JsonProperty("file_url")
    private String fileUrl;  // S3 URL

    @JsonProperty("message")
    private String message;  // 성공 메시지
}