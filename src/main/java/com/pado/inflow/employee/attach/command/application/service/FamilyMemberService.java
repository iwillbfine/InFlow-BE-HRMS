package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;

import java.util.List;

public interface FamilyMemberService {

    // 가족원 등록
    Boolean insertFamilyMembers(List<FamilyMemberDTO> familyMember);
}
