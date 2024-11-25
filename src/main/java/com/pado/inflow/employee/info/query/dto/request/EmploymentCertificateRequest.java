package com.pado.inflow.employee.info.query.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmploymentCertificateRequest {
    @JsonProperty("employee_id")
    private Long employeeId;
    @JsonProperty("purpose")
    private String purpose;
}