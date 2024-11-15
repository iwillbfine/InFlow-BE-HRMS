package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.CareerDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Career;
import com.pado.inflow.employee.attach.command.domain.repository.CareerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        try {
            return careerRepository.saveAll(careers.stream()
                    .map(mem -> modelMapper.map(mem, Career.class))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return null;
        }
    }

    // 사원의 경력정보 수정
    @Override
    public List<Career> modifyCareers(List<CareerDTO> careers) {
        try {
            return careerRepository.saveAllAndFlush(careers.stream()
                    .map(mem -> modelMapper.map(mem, Career.class))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return null;
        }
    }

    // 사원의 경력정보 삭제
    @Override
    public Boolean deleteCareers(List<Long> careers) {
        try {
            careerRepository.deleteAllById(careers);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
