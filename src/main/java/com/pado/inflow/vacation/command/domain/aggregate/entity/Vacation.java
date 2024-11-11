package com.pado.inflow.vacation.command.domain.aggregate.entity;

import com.pado.inflow.vacation.command.domain.aggregate.type.ExpirationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "vacation")
@NoArgsConstructor
@AllArgsConstructor
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    private Long vacationId;

    @Column(name = "vacation_name")
    private String vacationName;

    @Column(name = "vacation_left")
    private Long vacationLeft;

    @Column(name = "vacation_used")
    private Long vacationUsed;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "expiration_status")
    private ExpirationStatus expirationStatus;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "vacation_policy_id")
    private Long vacationPolicyId;

    @Column(name = "vacation_type_id")
    private Long vacationTypeId;

}
