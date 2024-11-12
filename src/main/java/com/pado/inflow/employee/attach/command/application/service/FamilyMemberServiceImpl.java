package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.FamilyMember;
import com.pado.inflow.employee.attach.command.domain.repository.FamilyMemberRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    // 가구원 등록
    public List<FamilyMember> insertFamilyMembers(List<FamilyMemberDTO> familyMember) {
        try {
            return(familyMemberRepository.saveAll(familyMember.stream()
                    .map(mem -> modelMapper.map(mem, FamilyMember.class))
                    .collect(Collectors.toList())));
        } catch (Exception e) {
            return null;
        }
    }

    // 가구원 수정
    public List<FamilyMember> modifyFamilyMembers(List<FamilyMemberDTO> familyMember) {
        try {
            return(familyMemberRepository.saveAllAndFlush(familyMember.stream()
                    .map(mem -> modelMapper.map(mem, FamilyMember.class))
                    .collect(Collectors.toList())));
        } catch (Exception e) {
            return null;
        }
    }

    // 가구원 삭제
    public Boolean deleteFamilyMember(List<Long> familyMember) {
        try {
            familyMemberRepository.deleteAllById(familyMember);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
