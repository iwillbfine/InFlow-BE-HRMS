package com.pado.inflow.employee.info.command.domain.repository;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    boolean existsByFileName(String fileName);
}