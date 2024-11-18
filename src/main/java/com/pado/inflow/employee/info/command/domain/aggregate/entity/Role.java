package com.pado.inflow.employee.info.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @Column(name = "role_code", nullable = false, length = 255)
    private String roleCode; // 직책 코드 (PK)

    @Column(name = "role_name", nullable = false, length = 255)
    private String roleName; // 직책 이름
}