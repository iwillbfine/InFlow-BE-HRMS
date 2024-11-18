package com.pado.inflow.employee.info.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "duty")
@Data
public class Duty {

    @Id
    @Column(name = "duty_code", nullable = false, length = 255)
    private String dutyCode; // 직무 코드 (PK)

    @Column(name = "duty_name", nullable = false, length = 255)
    private String dutyName; // 직무 이름
}