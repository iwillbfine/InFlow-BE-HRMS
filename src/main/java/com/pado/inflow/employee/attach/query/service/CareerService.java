package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.query.dto.CareerDTO;
import com.pado.inflow.employee.attach.query.repository.CareerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(careerMapper.getOneCareer(employeeId))
                .filter(career -> !career.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CAREER));
    }
}
