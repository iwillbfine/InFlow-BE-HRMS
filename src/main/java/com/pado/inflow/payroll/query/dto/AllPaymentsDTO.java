package com.pado.inflow.payroll.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllPaymentsDTO {
    private Long paymentId;
    private LocalDateTime paidAt;
    private int monthlySalary;
    private int actualSalary;
    private int nonTaxableSalary;
    private int totalDeductible;
    private Long employeeId;
}
