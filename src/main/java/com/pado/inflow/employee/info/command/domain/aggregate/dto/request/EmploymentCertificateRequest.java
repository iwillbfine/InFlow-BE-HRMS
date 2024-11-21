package com.pado.inflow.employee.info.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmploymentCertificateRequest {
    @JsonProperty("employee_number")
    private String employeeNumber;
    @JsonProperty("purpose")
    private String purpose;
}