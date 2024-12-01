package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllPaymentsDTO {
    @JsonProperty("payment_id")
    private Long paymentId;
    @JsonProperty("paid_at")
    private LocalDateTime paidAt;
    @JsonProperty("monthly_salary")
    private int monthlySalary;
    @JsonProperty("actual_salary")
    private int actualSalary;
    @JsonProperty("non_taxable_salary")
    private int nonTaxableSalary;
    @JsonProperty("bonus_and_allowance")
    private int bonusAndAllowance;
    @JsonProperty("total_deductible")
    private int totalDeductible;
    @JsonProperty("employee_id")
    private Long employeeId;
}
