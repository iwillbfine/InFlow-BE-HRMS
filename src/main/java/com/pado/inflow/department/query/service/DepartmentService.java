package com.pado.inflow.department.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.department.query.repository.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("departmentQueryService")
public class DepartmentService {
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    // 1. 검색창 검색어 입력을 통한 사원 리스트 조회
    public List<String> findEmployeesByKeyword(String keyword){
        // 검색어가 비어있거나 null일 경우 예외 처리
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }

        List<String> departmentMembers;
        try {
            // Mapper 호출
            departmentMembers = departmentMapper.findDepartmentMembersByKeyword(keyword);

            // 결과가 비어있는 경우 예외 처리
            if (departmentMembers == null || departmentMembers.isEmpty()) {
                throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT_MEMBER);
            }
        } catch (Exception e) {
            // Mapper 호출 중 다른 예외 발생 시 처리
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return departmentMembers;
    }


    // 2. 부서 코드를 통한 사원 리스트 조회
    public List<String> findEmployeesByDepartmentCode(String departmentCode){
        if(departmentCode == null || departmentCode.trim().isEmpty()){
            throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }
        List<String> departmentMembers;
        try {
            // Mapper 호출
            departmentMembers = departmentMapper.findDepartmentMembersByDepartmentCode(departmentCode);

            // 결과가 비어있는 경우 예외 처리
            if (departmentMembers == null || departmentMembers.isEmpty()) {
                throw new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT_MEMBER);
            }
        } catch (Exception e) {
            // Mapper 호출 중 다른 예외 발생 시 처리
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return departmentMembers;
    }





}
