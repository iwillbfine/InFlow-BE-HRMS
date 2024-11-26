package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.CareerDTO;

import java.util.List;

public interface CareerService {

    // 사원의 경력정보 등록
    List<CareerDTO> addCareers(List<CareerDTO> careers);

    // 사원의 경력정보 수정
    List<CareerDTO> modifyCareers(List<CareerDTO> careers);

    // 사원의 경력정보 삭제
    Boolean deleteCareers(List<Long> careers);
}
