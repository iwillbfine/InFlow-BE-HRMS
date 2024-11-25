package com.pado.inflow.payroll.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestNonTaxableDTO {

    @JsonProperty("non_taxable_name")
    private String nonTaxableName;

    @JsonProperty("amount")
    private Integer amount;
}
