package com.pado.inflow.payroll.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayrollDTO {

    private Long paymentId;
    private LocalDateTime paidAt;
    private int monthlySalary;
    private int actualSalary;
    private int nonTaxableAmount;
    private int familyMemberNum;
    private int validChildNum;
    private int totalWorkingDayNum;
    private int actualWorkingDayNum;
    private int paidVacationNum;
    private int unpaidVacationNum;
    private int publicHolidayNum;
    private int bonus;
    private int annualVacationAllowance;
    private int overtimeAllowance;
    private int nationalPensionDeductible;
    private int healthInsuranceDeductible;
    private int longTermCareInsuranceDeductible;
    private int employmentInsuranceDeductible;
    private int incomeTaxDeductible;
    private int localIncomeTaxDeductible;
    private int childDeductible;
    private int totalDeductible;
    private Long employeeId;
    private Long publicHolidayId;
    private Long earnedIncomeTaxId;

}
