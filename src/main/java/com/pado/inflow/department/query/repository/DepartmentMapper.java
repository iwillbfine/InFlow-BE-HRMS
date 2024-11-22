package com.pado.inflow.department.query.repository;


import com.pado.inflow.department.query.dto.GetDepartmentDetailDTO;
import com.pado.inflow.department.query.dto.GetDepartmentHierarchyDTO;
import com.pado.inflow.department.query.dto.GetDepartmentListByKeywordDTO;
import com.pado.inflow.department.query.dto.GetDepartmentMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    // 공통: 사원찾기 & 부서관리 - 폴더구조 ui
    // 전체 부서 불러오기
    List<GetDepartmentHierarchyDTO> findDepartmentHierarchy();
    List<GetDepartmentHierarchyDTO> findDepartmentHierarchyAsTree();

    // 1. 사원 코드, 사원명, 부서명의 검색 키워드를 통한 사원 목록 조회
    // keyword으로 검색하여 결과 조회
    List<GetDepartmentMemberDTO> findDepartmentMembersByKeyword(@Param("keyword") String keyword);

    // 2. 부서코드로 부서 해당하는 사원 목록 조회
    List<GetDepartmentMemberDTO> findDepartmentMembersByDepartmentCode(@Param("departmentCode") String departmentCode);


    // 3. 사원 코드로 사원 정보 상세 조회
    List<GetDepartmentMemberDTO> findDepartmentMemberDetailByMemberCode(@Param("employeeNumber") String employeeNumber);


    /* 관리자 권한 */
    // 1. 부서코드로 부서 상세정보 조회
    List<GetDepartmentDetailDTO> findDepartmentDetailByDepartmentCode(@Param("departmentCode") String departmentCode);

    // 2. 키워드를 통한 부서 목록 조회
    List<GetDepartmentListByKeywordDTO> findDepartmentListByKeyword(@Param("keyword") String keyword);










}
