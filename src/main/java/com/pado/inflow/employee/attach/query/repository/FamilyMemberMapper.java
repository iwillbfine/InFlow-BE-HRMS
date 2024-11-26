package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.FamilyMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyMemberMapper {

    // 사원의 가구원 조회
    List<FamilyMemberDTO> getAllFamilyMemberList(String employeeId);
}
