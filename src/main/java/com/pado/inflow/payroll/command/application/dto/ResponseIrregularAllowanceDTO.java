package com.pado.inflow.payroll.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseIrregularAllowanceDTO {

    @JsonProperty("irregular_allowance_id")
    private Long irregularAllowanceId;

    @JsonProperty("irregular_allowance_name")
    private String irregularAllowanceName;

    @JsonProperty("amount")
    private Integer amount;

}
