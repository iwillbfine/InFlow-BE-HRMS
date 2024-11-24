package com.pado.inflow.department.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.department.query.dto.*;
import com.pado.inflow.department.query.repository.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("departmentQueryService")
public class DepartmentService {

    // 로그
    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    // 공통: 사원찾기 & 부서관리 - 폴더구조 ui
    public List<DepartmentHierarchyDTO>findDepartmentHierarchy(){
        List<DepartmentHierarchyDTO> departmentHierarchyList;

        departmentHierarchyList = departmentMapper.findDepartmentHierarchy();

        if (departmentHierarchyList == null || departmentHierarchyList.isEmpty()){
            throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT);
        }
        return departmentHierarchyList;
    }
    // 데이터 계층 구조로 변환하기
    // db에서 데이터 조회 -> 해시맵으로 데이터 분류 -> 계층 구조 만들기
    public List<DepartmentHierarchyDTO> findDepartmentHierarchyAsTree(){
        // 플랫 리스트 조회
        List<DepartmentHierarchyDTO> departmentFlatList = findDepartmentHierarchy();

        // 플랫 리스트를 계층 구조로 변환
        // 플랫 리스트 변환 - 부서 코드를 키로 갖는 맵 형태로 변환
        Map<String, DepartmentHierarchyDTO> departmentHierarchyMap = departmentFlatList.stream()
                .collect(Collectors.toMap(DepartmentHierarchyDTO::getDepartmentCode, dept -> dept));

        List<DepartmentHierarchyDTO> hierarchy = new ArrayList<>();

        for (DepartmentHierarchyDTO department : departmentFlatList) {
            if (department.getUpperDepartmentCode() == null) {
                // 최상위 부서
                hierarchy.add(department);

            } else {
                // 상위 부서에 추가
                DepartmentHierarchyDTO parent = departmentHierarchyMap.get(department.getUpperDepartmentCode());
                if (parent != null) {
                    parent.getSubDepartments().add(department);
                }
            }
        }
        return hierarchy;

    }



    // 1. 검색창 검색어 입력을 통한 사원 리스트 조회
    public List<GetDepartmentMemberDTO> findEmployeesByKeyword(String keyword) {
        // 검색어가 비어있거나 null일 경우 예외 처리
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        // Mapper 호출
        List<GetDepartmentMemberDTO> departmentMembers = departmentMapper.findDepartmentMembersByKeyword(keyword);

        // 결과가 비어있는 경우 예외 처리
        if (departmentMembers == null || departmentMembers.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT_MEMBER);
        }

        return departmentMembers;
    }


    // 2. 부서 코드를 통한 사원 리스트 조회
    public List<GetDepartmentMemberDTO> findEmployeesByDepartmentCode(String departmentCode) {
        // 부서 코드가 비어있거나 null일 경우 예외 처리
        if (departmentCode == null || departmentCode.trim().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        // Mapper 호출
        List<GetDepartmentMemberDTO> departmentMembers = departmentMapper.findDepartmentMembersByDepartmentCode(departmentCode);

        // 결과가 비어있는 경우 예외 처리
        if (departmentMembers == null || departmentMembers.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT_MEMBER);
        }

        return departmentMembers;
    }

    //3. 선택한 사원 상세 정보 조회
    // 사원 코드를 통한 사원 상세 정보 조회
    public List<GetDepartmentMemberDTO> findEmployeeDetailByEmployeeNumber(String employeeNumber){
        if(employeeNumber == null || employeeNumber.trim().isEmpty()){
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        List<GetDepartmentMemberDTO> departmentMember = departmentMapper.findDepartmentMemberDetailByMemberCode(employeeNumber);
        // 결과가 비어있는 경우 예외 처리
        if (departmentMember == null || departmentMember.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT_MEMBER);
        }
        return departmentMember;
    }


    /* 인사팀 권한 - 부서 관리 */
    // 1. 부서 코드를 통한 부서 상세정보 조회
    public List<DepartmentDetailDTO> findDepartmentDetailByDepartmentCode(String departmentCode){
        if(departmentCode == null || departmentCode.trim().isEmpty()){
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        List<DepartmentDetailDTO> departmentDetail;
        try {
            departmentDetail = departmentMapper.findDepartmentDetailByDepartmentCode(departmentCode);
                if (departmentDetail == null || departmentDetail.isEmpty()) {

                    throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT);
                }
        } catch (Exception e) {
                throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return departmentDetail;

    }

    // 2. 키워드를 통한 부서 목록 조회
    public List<HrDepartmentListByKeywordDTO> findDepartmentListByKeyword(String keyword){
        if(keyword == null || keyword.trim().isEmpty()){
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }
        List<HrDepartmentListByKeywordDTO> departmentList;
        try{
            departmentList = departmentMapper.findDepartmentListByKeyword(keyword);
            if(departmentList == null || departmentList.isEmpty()){
                throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT);
            }
        } catch ( Exception e) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return departmentList;
    }

    /* 팀장 권한 - 내 부서 관리 */
    // 1. 부서 코드를 통한 사원 목록 조회
    // 2. 키워드를 통한 사원 목록 조회
    public List<ManagerDepartmentMemberListDTO> findDepartmentMemberListByDepartmentCode(String departmentCode, String keyword) {
        if (departmentCode == null || departmentCode.trim().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE); // 잘못된 부서 코드 입력
        }
        List<ManagerDepartmentMemberListDTO> departmentMemberList = departmentMapper.findDepartmentMemberListForManager(departmentCode, keyword);

        if (departmentMemberList == null || departmentMemberList.isEmpty()) {
            // 조회된 사원이 없는 경우
            throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT_MEMBER);
        }
        return departmentMemberList;
    }



}
