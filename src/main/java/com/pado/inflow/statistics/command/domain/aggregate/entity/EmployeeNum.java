package com.pado.inflow.statistics.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "EmployeeNum")
@Table(name = "monthly_employee_num_statistics")
public class EmployeeNum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private Long statisticsId;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "half")
    private String half;

    @Column(name = "total_employee_num")
    private Long totalEmployeeNum;

    @Column(name = "joined_employee_num")
    private Long joinedEmployeeNum;

    @Column(name = "lefted_employee_num")
    private Long leftedEmployeeNum;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
