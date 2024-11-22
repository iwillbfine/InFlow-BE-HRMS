package com.pado.inflow.department.command.domain.repository;

import com.pado.inflow.department.command.domain.aggregate.entity.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;

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
}