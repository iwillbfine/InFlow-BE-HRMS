package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.query.dto.CareerDTO;
import com.pado.inflow.employee.attach.query.repository.CareerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("CQueryService")
public class CareerService {

    private final CareerMapper careerMapper;

    @Autowired
    public CareerService(CareerMapper careerMapper) {
        this.careerMapper = careerMapper;
    }

    // 전 사원의 경력 조회
    public List<CareerDTO> getCareerAll() {
        return Optional.ofNullable(careerMapper.getAllCareers())
                .filter(career -> !career.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CAREER));
    }

    // 사원 한 명의 경력 조회
    public List<CareerDTO> getCareerOne(Long employeeId) {
        log.info(careerMapper.getOneCareer(employeeId).toString());
        return Optional.ofNullable(careerMapper.getOneCareer(employeeId))
                .filter(career -> !career.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CAREER));
    }
}
