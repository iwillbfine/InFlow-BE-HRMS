package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeverancePayDTO {

    @JsonProperty("severance_pay")
    private int severancePay; // 소수점 버림으로 인해 int 사용

    @JsonProperty("salary_details")
    private List<PeriodicPayDTO> salaryDetails;
}

