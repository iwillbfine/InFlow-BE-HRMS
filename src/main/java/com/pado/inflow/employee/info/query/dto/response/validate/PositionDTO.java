package com.pado.inflow.employee.info.query.dto.response.validate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PositionDTO {
    @JsonProperty("position_code")
    private String positionCode;

    @JsonProperty("position_name")
    private String positionName;
}
