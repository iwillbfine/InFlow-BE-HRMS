package com.pado.inflow.department.command.domain.repository;

import com.pado.inflow.department.command.domain.aggregate.entity.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DepartmentMemberRepository extends JpaRepository<DepartmentMember, Long> {

    /**
     * 특정 사원의 부서 구성원 데이터를 삭제
     */
    void deleteByEmployeeNumber(String employeeNumber);

    /**
     * 특정 사원의 사원 번호로 부서 구성원 존재 여부 확인
     *
     * @param employeeNumber 확인할 사원의 번호
     * @return 해당 사원 번호가 존재하면 true, 없으면 false
     */
    boolean existsByEmployeeNumber(String employeeNumber);

    // 이름을 기준으로 특정 부서원을 찾는 메서드
    Optional<DepartmentMember> findByName(String name);
}