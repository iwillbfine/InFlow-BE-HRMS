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
public class ResponseNonTaxableDTO {

    @JsonProperty("non_taxable_id")
    private Long nonTaxableId;

    @JsonProperty("non_taxable_name")
    private String nonTaxableName;

    @JsonProperty("amount")
    private int amount;


}
