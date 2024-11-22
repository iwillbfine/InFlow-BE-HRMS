package com.pado.inflow.payroll.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodicPayDTO {

    private String period;

    // 기간 별 일 수
    private Integer workingDays;

    // 계약 급여 (월 급여 + 비과세 급여)
    private Integer basePay;

    // 기타 수당 (초과근무 수당)
    private Integer extraPay;

}
