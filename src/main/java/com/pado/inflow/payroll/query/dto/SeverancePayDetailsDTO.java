package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeverancePayDetailsDTO {

    @JsonProperty("total_salary") // 총 급여 합계
    private Integer totalSalary;

    @JsonProperty("total_non_taxable_salary") // 총 비과세 급여 합계
    private Integer totalNonTaxableSalary;

    @JsonProperty("bonus_addition") // 상여금 계산값
    private Integer bonusAddition;

    @JsonProperty("leave_allowance_addition") // 연차수당 계산값
    private Integer leaveAllowanceAddition;

    @JsonProperty("severance_pay") // 총 퇴직금
    private Integer severancePay;

    @JsonProperty("three_months_ago") // 계산 기간 시작일
    private LocalDate threeMonthsAgo;

    @JsonProperty("severance_date") // 계산 기간 종료일
    private LocalDate severanceDate;

    @JsonProperty("join_date") // 입사일
    private LocalDate joinDate;
}
