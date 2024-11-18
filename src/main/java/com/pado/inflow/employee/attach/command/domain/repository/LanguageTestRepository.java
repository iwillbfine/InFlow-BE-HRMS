package com.pado.inflow.employee.attach.command.domain.repository;

import com.pado.inflow.employee.attach.command.domain.aggregate.entity.LanguageTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageTestRepository extends JpaRepository<LanguageTest, Long> {
}
