package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.AttendanceRequestDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
class AttendanceRequestServiceTests {

    @Autowired
    private AttendanceRequestService attendanceRequestService;

    @DisplayName("사원별 재택근무 신청 내역 미리보기 테스트")
    @Test
    void testFindRemoteRequestPreviewsByEmployeeId() {
        // Given
        Long employeeId = 5L;

        // When
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findRemoteRequestPreviewsByEmployeeId(employeeId);
        if (attendanceRequests != null && !attendanceRequests.isEmpty()) {
            attendanceRequests.forEach(x -> log.info(x.toString()));
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 재택근무 신청 내역 조회 테스트")
    @Test
    void testFindRemoteRequestsByEmployeeId() {
        // Given
        Long employeeId = 5L;
        Integer pageNo = 1;
        String date = "2024-11";

        // When
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findRemoteRequestsByEmployeeId(employeeId, pageNo, date);
        if (attendanceRequests != null) {
            log.info(attendanceRequests.toString());
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 초과근무 신청 내역 미리보기 테스트")
    @Test
    void testFindOvertimeRequestPreviewsByEmployeeId() {
        // Given
        Long employeeId = 3L;

        // When
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findOvertimeRequestPreviewsByEmployeeId(employeeId);
        if (attendanceRequests != null && !attendanceRequests.isEmpty()) {
            attendanceRequests.forEach(x -> log.info(x.toString()));
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 초과근무 신청 내역 조회 테스트")
    @Test
    void testFindOvertimeRequestsByEmployeeId() {
        // Given
        Long employeeId = 3L;
        Integer pageNo = 1;
        String date = "2024-11";

        // When
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findOvertimeRequestsByEmployeeId(employeeId, pageNo, date);
        if (attendanceRequests != null) {
            log.info(attendanceRequests.toString());
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 출장 신청 내역 미리보기 테스트")
    @Test
    void testFindBusinessTripRequestPreviewsByEmployeeId() {
        // Given
        Long employeeId = 1L;

        // When
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findBusinessTripRequestPreviewsByEmployeeId(employeeId);
        if (attendanceRequests != null && !attendanceRequests.isEmpty()) {
            attendanceRequests.forEach(x -> log.info(x.toString()));
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 출장 신청 내역 조회 테스트")
    @Test
    void testFindBusinessTripRequestsByEmployeeId() {
        // Given
        Long employeeId = 1L;
        Integer pageNo = 1;
        String date = "2024-11";

        // When
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findBusinessTripRequestsByEmployeeId(employeeId, pageNo, date);
        if (attendanceRequests != null) {
            log.info(attendanceRequests.toString());
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 파견 신청 내역 미리보기 테스트")
    @Test
    void testFindDispatchRequestPreviewsByEmployeeId() {
        // Given
        Long employeeId = 4L;

        // When
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findDispatchRequestPreviewsByEmployeeId(employeeId);
        if (attendanceRequests != null && !attendanceRequests.isEmpty()) {
            attendanceRequests.forEach(x -> log.info(x.toString()));
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 파견 신청 내역 조회 테스트")
    @Test
    void testFindDispatchRequestsByEmployeeId() {
        // Given
        Long employeeId = 4L;
        Integer pageNo = 1;
        String date = "2024-11";

        // When
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findDispatchRequestsByEmployeeId(employeeId, pageNo, date);
        if (attendanceRequests != null) {
            log.info(attendanceRequests.toString());
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 휴직 신청 내역 미리보기 테스트")
    @Test
    void testFindLeaveRequestPreviewsByEmployeeId() {
        // Given
        Long employeeId = 1L;

        // When
        List<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findLeaveRequestPreviewsByEmployeeId(employeeId);
        if (attendanceRequests != null && !attendanceRequests.isEmpty()) {
            attendanceRequests.forEach(x -> log.info(x.toString()));
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

    @DisplayName("사원별 휴직 신청 내역 조회 테스트")
    @Test
    void testFindLeaveRequestsByEmployeeId() {
        // Given
        Long employeeId = 1L;
        Integer pageNo = 1;
        String date = "2024-11";

        // When
        PageDTO<AttendanceRequestDTO> attendanceRequests =
                attendanceRequestService.findLeaveRequestsByEmployeeId(employeeId, pageNo, date);
        if (attendanceRequests != null) {
            log.info(attendanceRequests.toString());
        }

        // Then
        Assertions.assertNotNull(attendanceRequests);
    }

}
