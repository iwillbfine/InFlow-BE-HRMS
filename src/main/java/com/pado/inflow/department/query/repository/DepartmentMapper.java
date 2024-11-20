package com.pado.inflow.department.query.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    // 1. 사원 코드, 사원명, 부서명의 검색 키워드를 통한 사원 목록 조회
    // keyword으로 검색하여 결과 조회
    List<String> findDepartmentMembersByKeyword(@Param("keyword") String keyword);

    // 2. 부서코드로 부서 해당하는 사원 목록 조회
    List<String> findDepartmentMembersByDepartmentCode(@Param("departmentCode") String departmentCode);








}
