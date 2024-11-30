package com.pado.inflow.vacation.query.service;

import com.pado.inflow.vacation.query.dto.PageDTO;
import com.pado.inflow.vacation.query.dto.VacationDTO;

import java.util.List;

public interface VacationService {

    // 사원별 잔여 휴가 조회
    PageDTO<VacationDTO> findLeftVacationsByEmployeeId(Long employeeId, Integer pageNo);

    // 사원별 사용 휴가 조회
    PageDTO<VacationDTO> findUsedVacationsByEmployeeId(Long employeeId, Integer pageNo);

    // 사원별 잔여 휴가 전체 조회
    List<VacationDTO> findLeftAllVacationsByEmployeeId(Long employeeId);

}
