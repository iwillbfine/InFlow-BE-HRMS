package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.command.domain.repository.FamilyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FMCommandService")
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;

    @Autowired
    public FamilyMemberService(FamilyMemberRepository familyMemberRepository) {
        this.familyMemberRepository = familyMemberRepository;

    }

    public Boolean insertFamilyMembers(FamilyMemberDTO familyMember) {
        return true;
    }
}
