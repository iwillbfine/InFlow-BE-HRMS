package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.CareerDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Career;

import java.util.List;

public interface CareerService {

    // 사원의 경력정보 등록
    List<Career> addCareers(List<CareerDTO> careers);

}
