package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NonTaxableDTO {
    @JsonProperty("non_taxable_id")
    private Long nonTaxableId;
    @JsonProperty("non_taxable_name")
    private String nonTaxableName;
    @JsonProperty("amount")
    private int amount;
}
