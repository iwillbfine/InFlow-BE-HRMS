package com.pado.inflow.payroll.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "irregular_allowance")
@Entity
@Builder
public class IrregularAllowance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "irregular_allowance_id")
    private Long irregularAllowanceId;

    @Column(name = "irregular_allowance_name", nullable = false)
    private String irregularAllowanceName;

    @Column(name = "amount", nullable = false)
    private Integer amount;

}
