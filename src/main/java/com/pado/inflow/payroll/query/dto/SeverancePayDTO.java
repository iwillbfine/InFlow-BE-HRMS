package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeverancePayDTO {

    @JsonProperty("employee_id")
    private Long employeeId;

    // 입사일
    @JsonProperty("join_date")
    private LocalDate joinDate;

    // 프론트에서 넘어온 퇴직일
    @JsonProperty("severance_date")
    private LocalDate severanceDate;

    // 총 재직 일수
    @JsonProperty("total_working_days")
    private Integer totalWorkingDays;

    // 직전 3개월 급여 정보
    @JsonProperty("recent_three_months_pay")
    private PeriodicPayDTO recentThreeMonthsPay;

    // 연간 총 상여금
    @JsonProperty("total_bonus")
    private Integer totalBonus;

    // 3개월 간 근로 총 일수와 총 금액
    @JsonProperty("total_summary")
    private SummaryDTO totalSummary;

    // 평균 임금
    @JsonProperty("average_wage")
    private Integer averageWage;

    // 통상 임금
    @JsonProperty("ordinary_wage")
    private Integer ordinaryWage;

    // 퇴직금 결과
    @JsonProperty("severance_pay")
    private Integer severancePay;

}
