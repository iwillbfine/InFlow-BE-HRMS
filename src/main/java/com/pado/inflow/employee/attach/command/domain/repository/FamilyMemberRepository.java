package com.pado.inflow.employee.attach.command.domain.repository;

import com.pado.inflow.employee.attach.command.domain.aggregate.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
}
