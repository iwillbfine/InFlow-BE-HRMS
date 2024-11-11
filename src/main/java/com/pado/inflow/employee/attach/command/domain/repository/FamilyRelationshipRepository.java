package com.pado.inflow.employee.attach.command.domain.repository;

import com.pado.inflow.employee.attach.command.domain.aggregate.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, Long> {
}
