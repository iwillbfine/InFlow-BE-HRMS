package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.EducationDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Education;
import com.pado.inflow.employee.attach.command.domain.repository.EducationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Education> addEdus(List<EducationDTO> educations) {
        try {
            return educationRepository.saveAll(educations.stream()
                    .map(mem -> modelMapper.map(mem, Education.class))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return null;
        }
    }

    // 사원의 학력정보 수정
    @Override
    public List<Education> modifyEdus(List<EducationDTO> educations) {
        try {
            return educationRepository.saveAllAndFlush(educations.stream()
                    .map(mem -> modelMapper.map(mem, Education.class))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return null;
        }
    }

    // 사원의 학력정보 삭제

    @Override
    public Boolean deleteEdus(List<Long> educations) {
        try {
            educationRepository.deleteAllById(educations);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
