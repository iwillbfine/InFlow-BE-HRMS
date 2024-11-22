package com.pado.inflow.statistics.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "OvertimeAllowance")
@Table(name = "monthly_department_overtime_allowance_statistics")
public class OvertimeAllowance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private Long statisticsId;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "department_code")
    private String departmentCode;
}
