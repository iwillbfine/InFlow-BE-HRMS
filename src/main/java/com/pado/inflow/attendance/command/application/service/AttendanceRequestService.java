package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    ResponseLeaveReturnRequestDTO registLeaveRequest(RequestLeaveRequestDTO reqLeaveRequestDTO);

    // 복직 신청
    ResponseLeaveReturnRequestDTO registReturnRequest(RequestReturnRequestDTO reqReturnRequestDTO);

    // 근태신청 취소
    ResponseAttendanceRequestDTO cancelAttendanceRequest(Long attendanceRequestId, RequestCancelAttendanceRequestDTO reqCancelAttendanceRequestDTO);

    // 초과근무 연장
    ResponseCommuteRequestDTO extendOvertime(Long attendanceRequestId, RequestOvertimeExtensionDTO reqOvertimeExtensionDTO);

}
