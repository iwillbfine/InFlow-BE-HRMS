package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.CommuteDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;

public interface CommuteService {

    // 사원별 출퇴근 내역 조회
    PageDTO<CommuteDTO> findCommutesByEmployeeId(Long employeeId, Integer pageNo);

}
