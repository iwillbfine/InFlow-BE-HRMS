package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.EducationDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Education;
import com.pado.inflow.employee.attach.command.domain.repository.EducationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ECommandService")
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository,
                                ModelMapper modelMapper) {
        this.educationRepository = educationRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    // 사원의 학력정보 등록
    @Override
    public List<EducationDTO> addEdus(List<EducationDTO> educations) {
        return Optional.ofNullable(
                        educationRepository.saveAll(
                                        educations.stream()
                                                .map(dto -> modelMapper.map(dto, Education.class)) // DTO → 엔티티 변환
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(entity -> modelMapper.map(entity, EducationDTO.class)) // 엔티티 → DTO 변환
                                .collect(Collectors.toList())
                )
                .filter(list -> !list.isEmpty()) // 리스트가 비어있는지 검증
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 학력정보 수정
    @Override
    public List<EducationDTO> modifyEdus(List<EducationDTO> educations) {
        return Optional.ofNullable(
                        educationRepository.saveAllAndFlush(
                                        educations.stream()
                                                .map(dto -> modelMapper.map(dto, Education.class))
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(entity -> modelMapper.map(entity, EducationDTO.class))
                                .collect(Collectors.toList())
                )
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 학력정보 삭제
    @Override
    public Boolean deleteEdus(List<Long> educations) {
        return Optional.of(educations)
                .map(edu -> {
                    educationRepository.deleteAllById(edu);
                    return true;
                })
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
