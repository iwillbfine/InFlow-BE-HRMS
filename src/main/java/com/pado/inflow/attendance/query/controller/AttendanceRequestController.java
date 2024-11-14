package com.pado.inflow.attendance.query.controller;

import com.pado.inflow.attendance.query.dto.AttendanceRequestDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.service.AttendanceRequestService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryAttendanceRequestController")
@RequestMapping("/api/attendance-requests")
public class AttendanceRequestController {

    private final AttendanceRequestService attendanceRequestService;

    @Autowired
    public AttendanceRequestController(AttendanceRequestService attendanceRequestService) {
        this.attendanceRequestService = attendanceRequestService;
    }

    // 사원별 초과근무 신청 내역 미리보기 조회
    @GetMapping("/commute/overtime/preview")
    public ResponseDTO<?> getOvertimeRequestPreviewsByEmployeeId(@RequestParam("eid") Long employeeId) {
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findOvertimeRequestPreviewsByEmployeeId(employeeId);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 초과근무 신청 내역 전체 조회
    @GetMapping("/commute/overtime")
    public ResponseDTO<?> getOvertimeRequestsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                          @RequestParam("page") Integer pageNo) {
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findOvertimeRequestsByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 재택근무 신청 내역 미리보기 조회
    @GetMapping("/commute/remote/preview")
    public ResponseDTO<?> getRemoteRequestPreviewsByEmployeeId(@RequestParam("eid") Long employeeId) {
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findRemoteRequestPreviewsByEmployeeId(employeeId);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 재택근무 신청 내역 전체 조회
    @GetMapping("/commute/remote")
    public ResponseDTO<?> getRemoteRequestsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                        @RequestParam("page") Integer pageNo) {
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findRemoteRequestsByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 출장 신청 내역 미리보기 조회
    @GetMapping("/business-trip/preview")
    public ResponseDTO<?> getBusinessTripRequestPreviewsByEmployeeId(@RequestParam("eid") Long employeeId) {
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findBusinessTripRequestPreviewsByEmployeeId(employeeId);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 출장 신청 내역 전체 조회
    @GetMapping("/business-trip")
    public ResponseDTO<?> getBusinessTripRequestsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                              @RequestParam("page") Integer pageNo) {
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findBusinessTripRequestsByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 파견 신청 내역 미리보기 조회
    @GetMapping("/dispatch/preview")
    public ResponseDTO<?> getDispatchRequestPreviewsByEmployeeId(@RequestParam("eid") Long employeeId) {
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findDispatchRequestPreviewsByEmployeeId(employeeId);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 파견 신청 내역 전체 조회
    @GetMapping("/dispatch")
    public ResponseDTO<?> getDispatchRequestsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                          @RequestParam("page") Integer pageNo) {
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findDispatchRequestsByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 휴직 신청 내역 미리보기 조회
    @GetMapping("/leave/preview")
    public ResponseDTO<?> getLeaveRequestPreviewsByEmployeeId(@RequestParam("eid") Long employeeId) {
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findLeaveRequestPreviewsByEmployeeId(employeeId);
        return ResponseDTO.ok(attendanceRequests);
    }

    // 사원별 휴직 신청 내역 전체 조회
    @GetMapping("/leave")
    public ResponseDTO<?> getLeaveRequestsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                       @RequestParam("page") Integer pageNo) {
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findLeaveRequestsByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(attendanceRequests);
    }

}
