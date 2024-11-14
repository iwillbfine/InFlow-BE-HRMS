package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.LeaveReturnDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;

public interface LeaveReturnService {

    // 사웝별 휴복직 내역 조회
    PageDTO<LeaveReturnDTO> findLeaveReturnsByEmployeeId(Long employeeId, Integer pageNo);

}
