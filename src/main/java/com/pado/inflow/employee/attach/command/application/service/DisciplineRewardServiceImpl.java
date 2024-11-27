package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.DisciplineRewardDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.DisciplineReward;
import com.pado.inflow.employee.attach.command.domain.repository.DisciplineRewardRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("DRCommandService")
public class DisciplineRewardServiceImpl implements DisciplineRewardService {

    private final DisciplineRewardRepository disciplineRewardRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DisciplineRewardServiceImpl(DisciplineRewardRepository disciplineRewardRepository,
                                       ModelMapper modelMapper) {
        this.disciplineRewardRepository = disciplineRewardRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    // 사원의 경력정보 등록
    @Override
    public List<DisciplineRewardDTO> insertDisciplineRewards(List<DisciplineRewardDTO> drs) {
        return Optional.ofNullable(
                        disciplineRewardRepository.saveAll(
                                        drs.stream()
                                                .map(dto -> modelMapper.map(dto, DisciplineReward.class))
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(entity -> modelMapper.map(entity, DisciplineRewardDTO.class))
                                .collect(Collectors.toList())
                )
                .filter(careerList -> !careerList.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 경력정보 수정
    @Override
    public List<DisciplineRewardDTO> modifyDisciplineRewards(List<DisciplineRewardDTO> drs) {
        return Optional.ofNullable(
                        disciplineRewardRepository.saveAllAndFlush(
                                        drs.stream()
                                                .map(dto -> modelMapper.map(dto, DisciplineReward.class))
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(entity -> modelMapper.map(entity, DisciplineRewardDTO.class))
                                .collect(Collectors.toList())
                )
                .filter(careerList -> !careerList.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 경력정보 삭제
    @Override
    public Boolean deleteDisciplineRewards(List<Long> drs) {
        return Optional.ofNullable(drs)
                .map(ids -> {
                    disciplineRewardRepository.deleteAllById(ids);
                    return true;
                })
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
