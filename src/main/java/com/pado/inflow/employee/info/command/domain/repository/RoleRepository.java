package com.pado.inflow.employee.info.command.domain.repository;


import com.pado.inflow.employee.info.command.domain.aggregate.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    // role_code를 기반으로 role_name 조회
    @Query("SELECT r.roleName FROM Role r WHERE r.roleCode = :roleCode")
    Optional<String> findRoleNameByRoleCode(String roleCode);
}