package com.pado.inflow.payroll.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodicPayDTO {

    private String period;
    private Integer workingDays;
    private Integer totalPay;

}
