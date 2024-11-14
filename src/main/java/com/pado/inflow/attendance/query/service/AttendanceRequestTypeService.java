package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.AttendanceRequestTypeDTO;

import java.util.List;

public interface AttendanceRequestTypeService {

    // 근태 신청 유형 전체 조회
    List<AttendanceRequestTypeDTO> findAttendanceRequestTypes();

}
