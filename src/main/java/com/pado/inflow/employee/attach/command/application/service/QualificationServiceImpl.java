package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.QualificationDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Qualification;
import com.pado.inflow.employee.attach.command.domain.repository.QualificationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("QCommandService")
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QualificationServiceImpl(QualificationRepository qualificationRepository,
                                    ModelMapper modelMapper) {
        this.qualificationRepository = qualificationRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    // 사원의 자격증 정보 등록
    @Override
    public List<Qualification> addQualifications(List<QualificationDTO> quals) {
        return Optional.ofNullable(qualificationRepository.saveAll(quals.stream()
                .map(mem -> modelMapper.map(mem, Qualification.class))
                .collect(Collectors.toList())))
                .filter(ql -> !ql.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 자격증 정보 수정
    @Override
    public List<Qualification> modifyQualifications(List<QualificationDTO> quals) {
        return Optional.ofNullable(qualificationRepository.saveAllAndFlush(quals.stream()
                .map(mem -> modelMapper.map(mem, Qualification.class))
                .collect(Collectors.toList())))
                .filter(ql -> !ql.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 자격증 정보 삭제
    @Override
    public Boolean deleteQualifications(List<Long> quals) {
        return Optional.ofNullable(quals)
                .map(qul -> {
                    qualificationRepository.deleteAllById(qul);
                    return true;
                })
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
