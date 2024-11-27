package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.CommuteDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;

public interface CommuteQueryService {

    // 사원별 출퇴근 내역 조회
    PageDTO<CommuteDTO> findCommutesByEmployeeId(Long employeeId, Integer pageNo, String date);

    // 당일 재택 출퇴근 내역 조회
    CommuteDTO findTodayRemoteByEmployeeId(Long employeeId);

    // 당일 출퇴근 내역 조회
    CommuteDTO findTodayCommuteByEmployeeId(Long employeeId);

}
