package com.pado.inflow.employee.info.query.dto.response.validate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DutyDTO {
    @JsonProperty("duty_code")
    private String dutyCode;

    @JsonProperty("duty_name")
    private String dutyName;
}
