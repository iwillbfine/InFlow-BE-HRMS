package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.CareerDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Career;
import com.pado.inflow.employee.attach.command.domain.repository.CareerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("CCommandService")
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CareerServiceImpl(CareerRepository careerRepository,
                             ModelMapper modelMapper) {
        this.careerRepository = careerRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    // 사원의 경력정보 등록
    @Override
    public List<Career> addCareers(List<CareerDTO> careers) {
        return Optional.ofNullable(careerRepository.saveAll(careers.stream()
                .map(mem -> modelMapper.map(mem, Career.class))
                .collect(Collectors.toList())))
                .filter(career -> !career.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 경력정보 수정
    @Override
    public List<Career> modifyCareers(List<CareerDTO> careers) {
        return Optional.ofNullable(careerRepository.saveAllAndFlush(careers.stream()
                .map(mem -> modelMapper.map(mem, Career.class))
                .collect(Collectors.toList())))
                .filter(career -> !career.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 경력정보 삭제
    @Override
    public Boolean deleteCareers(List<Long> careers) {
        return Optional.ofNullable(careers)
                .map(ids -> {
                    careerRepository.deleteAllById(ids);
                    return true;
                })
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
