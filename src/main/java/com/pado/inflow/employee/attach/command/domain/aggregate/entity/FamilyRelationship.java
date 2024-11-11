package com.pado.inflow.employee.attach.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_relationship_code")
    private Long familyRelationshipCode;

    @Column(name = "family_relationship_name")
    private String familyRelationshipName;
}
