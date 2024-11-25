package com.pado.inflow.payroll.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestIrregularAllowanceDTO {

    @JsonProperty("irregular_allowance_name")
    private String irregularAllowanceName;

    @JsonProperty("amount")
    private Integer amount;

}
