package com.pado.inflow.employee.info.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestPasswordDTO {

    @JsonProperty("employee_id") // 사원ID
    private Long employeeId;

    @JsonProperty("new_password") // 새 비번
    private String newPassword;
}
