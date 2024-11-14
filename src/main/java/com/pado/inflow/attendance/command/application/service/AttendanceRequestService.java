package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.*;

public interface AttendanceRequestService {

    // 재택근무 신청
    ResponseCommuteRequestDTO registRemoteRequest(RequestCommuteRequestDTO reqCommuteRequestDTO);

    // 초과근무 신청
    ResponseCommuteRequestDTO registOvertimeRequest(RequestCommuteRequestDTO reqCommuteRequestDTO);

    // 출장 신청
    ResponseBusinessTripRequestDTO registBusinessTripRequest(RequestBusinessTripRequestDTO reqBusinessTripRequestDTO);

    // 파견 신청
    ResponseBusinessTripRequestDTO registDispatchRequest(RequestBusinessTripRequestDTO reqBusinessTripRequestDTO);

    // 휴직 신청
    ResponseLeaveReturnRequestDTO registLeaveReturnRequest(RequestLeaveReturnRequestDTO reqLeaveReturnRequestDTO);

    // 복직 처리
    ResponseLeaveReturnRequestDTO reinstate(Long attendanceRequestId);

    // 초과근무 연장
    ResponseCommuteRequestDTO extendOvertime(Long attendanceRequestId);

}
