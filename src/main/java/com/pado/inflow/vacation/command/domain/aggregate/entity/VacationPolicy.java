package com.pado.inflow.vacation.command.domain.aggregate.entity;

import com.pado.inflow.vacation.command.domain.aggregate.type.PaidStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "vacation_policy")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VacationPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_policy_id")
    private Long vacationPolicyId;

    @Column(name = "vacation_policy_name")
    private String vacationPolicyName;

    @Column(name = "allocation_days")
    private Long allocationDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "paid_status")
    private PaidStatus paidStatus;

    @Column(name = "year")
    private Integer year;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "auto_allocation_cycle")
    private String autoAllocationCycle;

    @Column(name = "vacation_type_id")
    private Long vacationTypeId;

    @Column(name = "policy_register_id")
    private Long policyRegisterId;

}
