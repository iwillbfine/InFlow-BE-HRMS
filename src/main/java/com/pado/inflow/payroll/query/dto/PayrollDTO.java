package com.pado.inflow.payroll.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayrollDTO {

    @JsonProperty("payment_id")
    private Long paymentId;
    @JsonProperty("paid_at")
    private LocalDateTime paidAt;
    @JsonProperty("monthly_salary")
    private int monthlySalary;
    @JsonProperty("actual_salary")
    private int actualSalary;
    @JsonProperty("non_taxable_amount")
    private int nonTaxableAmount;
    @JsonProperty("family_member_num")
    private int familyMemberNum;
    @JsonProperty("valid_child_num")
    private int validChildNum;
    @JsonProperty("total_working_day_num")
    private int totalWorkingDayNum;
    @JsonProperty("actual_working_day_num")
    private int actualWorkingDayNum;
    @JsonProperty("paid_vacation_num")
    private int paidVacationNum;
    @JsonProperty("unpaid_vacation_num")
    private int unpaidVacationNum;
    @JsonProperty("public_holiday_num")
    private int publicHolidayNum;
    @JsonProperty("bonus")
    private int bonus;
    @JsonProperty("annual_vacation_allowance")
    private int annualVacationAllowance;
    @JsonProperty("overtime_allowance")
    private int overtimeAllowance;
    @JsonProperty("national_pension_deductible")
    private int nationalPensionDeductible;
    @JsonProperty("health_insurance_deductible")
    private int healthInsuranceDeductible;
    @JsonProperty("long_term_care_insurance_deductible")
    private int longTermCareInsuranceDeductible;
    @JsonProperty("employment_insurance_deductible")
    private int employmentInsuranceDeductible;
    @JsonProperty("income_tax_deductible")
    private int incomeTaxDeductible;
    @JsonProperty("local_income_tax_deductible")
    private int localIncomeTaxDeductible;
    @JsonProperty("child_deductible")
    private int childDeductible;
    @JsonProperty("total_deductible")
    private int totalDeductible;
    @JsonProperty("employee_id")
    private Long employeeId;
    @JsonProperty("public_holiday_id")
    private Long publicHolidayId;
    @JsonProperty("earned_income_tax_id")
    private Long earnedIncomeTaxId;

}
