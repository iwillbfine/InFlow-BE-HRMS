package com.pado.inflow.payroll.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NonTaxableDTO {
    private Long nonTaxableId;
    private String nonTaxableName;
    private int amount;
}
