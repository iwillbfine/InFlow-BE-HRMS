package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.FamilyMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FamilyMemberMapper {

    // 전 사원의 가족구성원 조회
    List<FamilyMemberDTO> getAllFamilyMemberList();

    // 사원 한 명의 가족구성원 조회
    List<FamilyMemberDTO> getOneFamilyMemberList(Long employeeId);
}
