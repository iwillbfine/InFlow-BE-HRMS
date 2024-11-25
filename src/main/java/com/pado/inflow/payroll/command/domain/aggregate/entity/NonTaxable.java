package com.pado.inflow.payroll.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "non_taxable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NonTaxable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "non_taxable_id")
    private Long nonTaxableId;

    @Column(name = "non_taxable_name", nullable = false)
    private String nonTaxableName;

    @Column(name="amount", nullable = false)
    private Integer amount;
}
