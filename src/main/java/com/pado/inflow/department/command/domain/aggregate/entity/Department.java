package com.pado.inflow.department.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "department")
@Data
public class Department {

    @Id
    @Column(name = "department_code")
    private String departmentCode;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "disbanded_at")
    private LocalDateTime disbandedAt;

    @Column(name = "min_employee_num")
    private Integer minEmployeeNum;

    @Column(name = "upper_department_code")
    private String upperDepartmentCode;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); // 생성 시 자동으로 현재 시간 설정
    }
}