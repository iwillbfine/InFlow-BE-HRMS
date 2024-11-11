package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.employee.attach.query.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.query.repository.FamilyMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FMQueryService")
public class FamilyMemberService {

    static FamilyMemberMapper familyMemberMapper;

    @Autowired
    public FamilyMemberService(FamilyMemberMapper familyMemberMapper) {
        this.familyMemberMapper = familyMemberMapper;
    }

    public List<FamilyMemberDTO> getFamilyMemberAll() {
        return familyMemberMapper.getAllFamilyMemberList();
    }

    public List<FamilyMemberDTO> getFamilyMemberOne(Long employeeId) {
        return familyMemberMapper.getOneFamilyMemberList(employeeId);
    }
}
