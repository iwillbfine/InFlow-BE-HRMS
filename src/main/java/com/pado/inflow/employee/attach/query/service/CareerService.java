package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.employee.attach.query.dto.CareerDTO;
import com.pado.inflow.employee.attach.query.repository.CareerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CQueryService")
public class CareerService {

    private final CareerMapper careerMapper;

    @Autowired
    public CareerService(CareerMapper careerMapper) {
        this.careerMapper = careerMapper;
    }

    // 전 사원의 학력 조회
    public List<CareerDTO> getCareerAll() {
        return careerMapper.getAllCareers();
    }

    // 사원 한 명의 학력 조회
   public List<CareerDTO> getCareerOne(Long employeeId) {
        return careerMapper.getOneCareer(employeeId);
    }
}
