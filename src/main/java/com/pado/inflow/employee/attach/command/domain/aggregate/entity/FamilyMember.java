package com.pado.inflow.employee.attach.command.domain.aggregate.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "family_member")
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_member_id")
    private Long familyMemberId;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "family_relationship_code")
    private Long familyRelationshipCode;
}
