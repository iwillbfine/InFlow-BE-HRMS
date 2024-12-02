package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.CommuteDTO;
import com.pado.inflow.attendance.query.dto.ResponseCommuteDTO;

import java.util.List;

public interface CommuteQueryService {

    // 사원별 출퇴근 내역 조회
    List<ResponseCommuteDTO> findCommutesByEmployeeId(Long employeeId, String date);

    // 사원별 초과근무 내역 조회
    List<CommuteDTO> findOvertimesByEmployeeId(Long employeeId, String date);

    // 당일 재택 출퇴근 내역 조회
    CommuteDTO findTodayRemoteByEmployeeId(Long employeeId);

    // 당일 출퇴근 내역 조회
    CommuteDTO findTodayCommuteByEmployeeId(Long employeeId);

}
