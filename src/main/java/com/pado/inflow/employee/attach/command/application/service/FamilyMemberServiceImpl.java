package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.FamilyMemberDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.FamilyMember;
import com.pado.inflow.employee.attach.command.domain.repository.FamilyMemberRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<FamilyMemberDTO> insertFamilyMembers(List<FamilyMemberDTO> familyMember) {
        return Optional.ofNullable(
                        familyMemberRepository.saveAll(
                                        familyMember.stream()
                                                .map(dto -> modelMapper.map(dto, FamilyMember.class))
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(entity -> modelMapper.map(entity, FamilyMemberDTO.class))
                                .collect(Collectors.toList())
                )
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 가구원 수정
    public List<FamilyMemberDTO> modifyFamilyMembers(List<FamilyMemberDTO> familyMember) {
        return Optional.ofNullable(
                        familyMemberRepository.saveAllAndFlush(
                                        familyMember.stream()
                                                .map(dto -> modelMapper.map(dto, FamilyMember.class))
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(entity -> modelMapper.map(entity, FamilyMemberDTO.class))
                                .collect(Collectors.toList())
                )
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 가구원 삭제
    public Boolean deleteFamilyMember(List<Long> familyMember) {
        return Optional.ofNullable(familyMember)
                .map(fam -> {
                    familyMemberRepository.deleteAllById(familyMember);
                    return true;
                })
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
