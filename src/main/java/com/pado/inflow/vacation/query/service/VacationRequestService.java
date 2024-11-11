package com.pado.inflow.vacation.query.service;

import com.pado.inflow.vacation.query.dto.PageDTO;
import com.pado.inflow.vacation.query.dto.VacationRequestDTO;

import java.util.List;

public interface VacationRequestService {

    // 사원별 휴가 신청 내역 미리보기 조회
    List<VacationRequestDTO> findVacationRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 휴가 신청 내역 전체 조회
    PageDTO<VacationRequestDTO> findVacationRequestsByEmployeeId(Long employeeId, Integer pageNo);

}
