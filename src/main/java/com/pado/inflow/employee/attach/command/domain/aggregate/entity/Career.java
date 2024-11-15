package com.pado.inflow.employee.attach.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "Career")
@Table(name = "career")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id")
    private Long careerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "resignation_date")
    private LocalDate resignationDate;

    @Column(name = "employee_id")
    private Long employeeId;
}





