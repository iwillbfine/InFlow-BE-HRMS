package com.pado.inflow.attendance.command.application.controller;

import com.pado.inflow.attendance.command.application.dto.*;

import com.pado.inflow.attendance.command.application.service.AttendanceRequestService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @PostMapping("/leave")
    public ResponseDTO<?> createLeavenRequest(
            @RequestParam("request_reason") String requestReason,
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate,
            @RequestParam("employee_id") Long employeeId,
            @RequestParam("attendance_request_type_id") Long attendanceRequestTypeId,
            @RequestParam("attachments") List<MultipartFile> attachments) {

        // DTO 객체 생성
        RequestLeaveRequestDTO reqLeaveRequestDTO = RequestLeaveRequestDTO.builder()
                .requestReason(requestReason)
                .startDate(startDate)
                .endDate(endDate)
                .employeeId(employeeId)
                .attendanceRequestTypeId(attendanceRequestTypeId)
                .attachments(attachments)
                .build();

        // 서비스 호출
        ResponseLeaveReturnRequestDTO resLeaveReturnRequestDTO =
                attendanceRequestService.registLeaveRequest(reqLeaveRequestDTO);
        return ResponseDTO.ok(resLeaveReturnRequestDTO);
    }

    // 복직 신청
    @PostMapping("/return")
    public ResponseDTO<?> createReturnRequest(
            @RequestParam("attendance_request_id") Long attendanceRequestId,
            @RequestParam("request_reason") String requestReason,
            @RequestParam("end_date") String endDate,
            @RequestParam("employee_id") Long employeeId,
            @RequestParam("attendance_request_type_id") Long attendanceRequestTypeId,
            @RequestParam("attachments") List<MultipartFile> attachments) {

        // DTO 객체 생성
        RequestReturnRequestDTO reqReturnRequestDTO = RequestReturnRequestDTO.builder()
                .attendanceRequestId(attendanceRequestId)
                .requestReason(requestReason)
                .endDate(endDate)
                .employeeId(employeeId)
                .attendanceRequestTypeId(attendanceRequestTypeId)
                .attachments(attachments)
                .build();

        // 서비스 호출
        ResponseLeaveReturnRequestDTO resReturnRequestDTO =
                attendanceRequestService.registReturnRequest(reqReturnRequestDTO);
        return ResponseDTO.ok(resReturnRequestDTO);
    }

    // 근태신청 취소
    @PatchMapping("/{attendanceRequestId}")
    public ResponseDTO<?> cancelAttendanceRequest(@PathVariable("attendanceRequestId") Long attendanceRequestId,
                                                  @RequestBody RequestCancelAttendanceRequestDTO reqCancelAttendanceRequestDTO) {
        ResponseAttendanceRequestDTO resAttendanceRequestDTO =
                attendanceRequestService.cancelAttendanceRequest(attendanceRequestId, reqCancelAttendanceRequestDTO);
        return ResponseDTO.ok(resAttendanceRequestDTO);
    }

    // 초과근무 연장
    @PatchMapping("/commute/overtime/{attendanceRequestId}")
    public ResponseDTO<?> extendOvertime(@PathVariable("attendanceRequestId") Long attendanceRequestId,
                                         @RequestBody RequestOvertimeExtensionDTO reqOvertimeExtensionDTO) {
        ResponseCommuteRequestDTO resCommuteRequestDTO =
                attendanceRequestService.extendOvertime(attendanceRequestId, reqOvertimeExtensionDTO);
        return ResponseDTO.ok(resCommuteRequestDTO);
    }

}
