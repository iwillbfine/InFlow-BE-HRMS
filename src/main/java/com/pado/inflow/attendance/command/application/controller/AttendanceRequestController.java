package com.pado.inflow.attendance.command.application.controller;

import com.pado.inflow.attendance.command.application.dto.*;

import com.pado.inflow.attendance.command.application.service.AttendanceRequestService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandAttendanceRequestController")
@RequestMapping("/api/attendance-requests")
public class AttendanceRequestController {

    private final AttendanceRequestService attendanceRequestService;

    @Autowired
    public AttendanceRequestController(AttendanceRequestService attendanceRequestService) {
        this.attendanceRequestService = attendanceRequestService;
    }

    // 재택근무 신청
    @PostMapping("/commute/remote")
    public ResponseDTO<?> createRemoteRequest(@RequestBody RequestCommuteRequestDTO reqCommuteRequestDTO) {
        ResponseCommuteRequestDTO resCommuteRequestDTO =
                attendanceRequestService.registRemoteRequest(reqCommuteRequestDTO);
        return ResponseDTO.ok(resCommuteRequestDTO);
    }

    // 초과근무 신청
    @PostMapping("/commute/overtime")
    public ResponseDTO<?> createOvertimeRequest(@RequestBody RequestCommuteRequestDTO reqCommuteRequestDTO) {
        ResponseCommuteRequestDTO resCommuteRequestDTO =
                attendanceRequestService.registOvertimeRequest(reqCommuteRequestDTO);
        return ResponseDTO.ok(resCommuteRequestDTO);
    }

    // 출장 신청
    @PostMapping("/business-trip")
    public ResponseDTO<?> createBusinessTripRequest(@RequestBody RequestBusinessTripRequestDTO reqBusinessTripRequestDTO) {
        ResponseBusinessTripRequestDTO resBusinessTripRequestDTO =
                attendanceRequestService.registBusinessTripRequest(reqBusinessTripRequestDTO);
        return ResponseDTO.ok(resBusinessTripRequestDTO);
    }

    // 파견 신청
    @PostMapping("/dispatch")
    public ResponseDTO<?> createDispatchRequest(@RequestBody RequestBusinessTripRequestDTO reqBusinessTripRequestDTO) {
        ResponseBusinessTripRequestDTO resBusinessTripRequestDTO =
                attendanceRequestService.registDispatchRequest(reqBusinessTripRequestDTO);
        return ResponseDTO.ok(resBusinessTripRequestDTO);
    }

    // 휴직 신청
    @PostMapping("/leave-return")
    public ResponseDTO<?> createLeaveReturnRequest(@RequestBody RequestLeaveReturnRequestDTO reqLeaveReturnRequestDTO) {
        ResponseLeaveReturnRequestDTO resLeaveReturnRequestDTO =
                attendanceRequestService.registLeaveReturnRequest(reqLeaveReturnRequestDTO);
        return ResponseDTO.ok(resLeaveReturnRequestDTO);
    }

    // 복직 처리
    @PatchMapping("/leave-return/{attendanceRequestId}")
    public ResponseDTO<?> reinstate(@PathVariable("attendanceRequestId") Long attendanceRequestId) {
        ResponseLeaveReturnRequestDTO resLeaveReturnRequestDTO =
                attendanceRequestService.reinstate(attendanceRequestId);
        return ResponseDTO.ok(resLeaveReturnRequestDTO);
    }

    // 초과근무 연장
    @PatchMapping("/commute/overtime/{attendanceRequestId}")
    public ResponseDTO<?> extendOvertime(@PathVariable("attendanceRequestId") Long attendanceRequestId,
                                                @RequestBody RequestOvertimeExtensionDTO reqOvertimeExtensionDTO) {
        ResponseCommuteRequestDTO resLeaveReturnRequestDTO =
                attendanceRequestService.extendOvertime(attendanceRequestId);
        return ResponseDTO.ok(resLeaveReturnRequestDTO);
    }

}
