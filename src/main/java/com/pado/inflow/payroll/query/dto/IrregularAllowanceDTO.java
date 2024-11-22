package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IrregularAllowanceDTO {
    @JsonProperty("irregular_allowance_id")
    private Long irregularAllowanceId;
    @JsonProperty("irregular_allowance_name")
    private String irregularAllowanceName;
    @JsonProperty("amount")
    private int amount;
}
