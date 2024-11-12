package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.FamilyMember;
import com.pado.inflow.employee.attach.command.domain.repository.FamilyMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("FMCommandService")
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FamilyMemberServiceImpl(FamilyMemberRepository familyMemberRepository,
                                   ModelMapper modelMapper) {
        this.familyMemberRepository = familyMemberRepository;
        this.modelMapper = modelMapper;

    }

    public Boolean insertFamilyMembers(List<FamilyMemberDTO> familyMember) {
        try {
            List<FamilyMember> list = familyMemberRepository.saveAll(familyMember.stream()
                    .map(mem -> modelMapper.map(mem, FamilyMember.class))
                    .collect(Collectors.toList()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
