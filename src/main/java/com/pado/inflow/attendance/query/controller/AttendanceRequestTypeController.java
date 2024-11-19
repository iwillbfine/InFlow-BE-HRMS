package com.pado.inflow.attendance.query.controller;

import com.pado.inflow.attendance.query.dto.AttendanceRequestTypeDTO;
import com.pado.inflow.attendance.query.service.AttendanceRequestTypeService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryAttendanceRequestTypeController")
@RequestMapping("/api/attendance-request-types")
public class AttendanceRequestTypeController {

    private final AttendanceRequestTypeService attendanceRequestTypeService;

    @Autowired
    public AttendanceRequestTypeController(AttendanceRequestTypeService attendanceRequestTypeService) {
        this.attendanceRequestTypeService = attendanceRequestTypeService;
    }

    // 근태 신청 유형 전체 조회
    @GetMapping
    public ResponseDTO<?> getAttendanceRequestTypes() {
        List<AttendanceRequestTypeDTO> attendanceRequestTypes = attendanceRequestTypeService.findAttendanceRequestTypes();
        return ResponseDTO.ok(attendanceRequestTypes);
    }

}
