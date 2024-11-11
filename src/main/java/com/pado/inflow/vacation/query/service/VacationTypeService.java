package com.pado.inflow.vacation.query.service;

import com.pado.inflow.vacation.query.dto.VacationTypeDTO;

import java.util.List;

public interface VacationTypeService {

    // 휴가 종류 전체 조회
    List<VacationTypeDTO> findVacationTypes();

}
