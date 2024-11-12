package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.FamilyMember;

import java.util.List;

public interface FamilyMemberService {

    // 가구원 등록
    List<FamilyMember> insertFamilyMembers(List<FamilyMemberDTO> familyMember);

    // 가구원 수정
    List<FamilyMember> modifyFamilyMembers(List<FamilyMemberDTO> familyMember);

    // 가구원 삭제
    Boolean deleteFamilyMember(List<Long> familyMember);
}
