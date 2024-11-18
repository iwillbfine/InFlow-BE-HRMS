package com.pado.inflow.employee.info.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "position")
@Data
public class Position {
    @Id
    @Column(name = "position_code", nullable = false, length = 255)
    private String positionCode;

    @Column(name = "position_name", nullable = false, length = 255)
    private String positionName;
}
