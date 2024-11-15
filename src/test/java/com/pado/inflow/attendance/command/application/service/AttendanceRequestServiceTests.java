package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
@Transactional
class AttendanceRequestServiceTests {

    @Autowired
    private AttendanceRequestService attendanceRequestService;

    @DisplayName("재택근무 신청 테스트")
    @Test
    void testRegistRemoteRequest() {
        // Given
        RequestCommuteRequestDTO reqCommuteRequestDTO = RequestCommuteRequestDTO
                .builder()
                .requestReason("오늘은 재택근무 하는 날")
                .startDate(LocalDate.now().toString())
                .employeeId(1L)
                .attendanceRequestTypeId(1L)
                .build();

        // When
        ResponseCommuteRequestDTO resCommuteRequestDTO = attendanceRequestService.registRemoteRequest(reqCommuteRequestDTO);
        if (resCommuteRequestDTO != null) {
            log.info(resCommuteRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resCommuteRequestDTO);
    }

    @DisplayName("초과근무 신청 테스트")
    @Test
    void testRegistOvertimeRequest() {
        // Given
        LocalDate now = LocalDate.now();
        LocalDateTime after = now.atTime(18,0);

        int minute = after.getMinute();
        int roundedMinute = (minute < 30) ? 30 : 0;

        LocalDateTime startTime;

        // 만약 0분이 되면 시간을 1시간 뒤로 설정
        if (roundedMinute == 0) {
            startTime = after.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        } else {
            startTime = after.withMinute(roundedMinute).withSecond(0).withNano(0);
        }

        LocalDateTime endTime = startTime.plusMinutes(30);

        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        RequestCommuteRequestDTO reqCommuteRequestDTO = RequestCommuteRequestDTO
                .builder()
                .requestReason("야근해야합니다")
                .startDate(formattedStartTime)
                .endDate(formattedEndTime)
                .employeeId(1L)
                .attendanceRequestTypeId(2L)
                .build();

        // When
        ResponseCommuteRequestDTO resCommuteRequestDTO = attendanceRequestService.registOvertimeRequest(reqCommuteRequestDTO);
        if (resCommuteRequestDTO != null) {
            log.info(resCommuteRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resCommuteRequestDTO);
    }

    @DisplayName("출장 신청 테스트")
    @Test
    void testRegistBusinessTripRequest() {
        // Given
        RequestBusinessTripRequestDTO reqBusinessTripRequestDTO = RequestBusinessTripRequestDTO
                .builder()
                .requestReason("출장 가야합니다.")
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().plusDays(3).toString())
                .destination("실리콘밸리 본사")
                .employeeId(1L)
                .attendanceRequestTypeId(3L)
                .build();

        // When
        ResponseBusinessTripRequestDTO resBusinessTripRequestDTO =
                attendanceRequestService.registBusinessTripRequest(reqBusinessTripRequestDTO);
        if (resBusinessTripRequestDTO != null) {
            log.info(resBusinessTripRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resBusinessTripRequestDTO);
    }

    @DisplayName("파견 신청 테스트")
    @Test
    void testRegistDispatchRequest() {
        // Given
        RequestBusinessTripRequestDTO reqBusinessTripRequestDTO = RequestBusinessTripRequestDTO
                .builder()
                .requestReason("파견 가야합니다.")
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().plusDays(10).toString())
                .destination("LA 지사")
                .employeeId(1L)
                .attendanceRequestTypeId(4L)
                .build();

        // When
        ResponseBusinessTripRequestDTO resBusinessTripRequestDTO =
                attendanceRequestService.registDispatchRequest(reqBusinessTripRequestDTO);
        if (resBusinessTripRequestDTO != null) {
            log.info(resBusinessTripRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resBusinessTripRequestDTO);
    }

    @DisplayName("휴직 신청 테스트")
    @Test
    void testRegistLeaveRequest() {
        // Given
        List<Map<String, String>> attachments = new ArrayList<>();

        Map<String, String> file = new HashMap<>();
        file.put("file_name", "테스트용 첨부파일");
        file.put("file_url", "https://aws.com/inflow/file.pdf");

        Map<String, String> file2 = new HashMap<>();
        file2.put("file_name", "테스트용 첨부파일2");
        file2.put("file_url", "https://aws.com/inflow/file2.pdf");

        attachments.add(file);
        attachments.add(file2);

        RequestLeaveRequestDTO reqLeaveRequestDTO = RequestLeaveRequestDTO
                .builder()
                .requestReason("육아휴직")
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().plusDays(60).toString())
                .employeeId(1L)
                .attendanceRequestTypeId(5L)
                .attachments(attachments)
                .build();

        // When
        ResponseLeaveReturnRequestDTO resLeaveRequestDTO =
                attendanceRequestService.registLeaveRequest(reqLeaveRequestDTO);
        if (resLeaveRequestDTO != null) {
            log.info(resLeaveRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resLeaveRequestDTO);
    }

    @DisplayName("복직 신청 테스트")
    @Test
    void testRegistReturnRequest() {
        // Given
        List<Map<String, String>> attachments = new ArrayList<>();

        Map<String, String> file = new HashMap<>();
        file.put("file_name", "테스트용 첨부파일");
        file.put("file_url", "https://aws.com/inflow/file.pdf");

        Map<String, String> file2 = new HashMap<>();
        file2.put("file_name", "테스트용 첨부파일2");
        file2.put("file_url", "https://aws.com/inflow/file2.pdf");

        attachments.add(file);
        attachments.add(file2);

        RequestReturnRequestDTO reqRequestRequestDTO = RequestReturnRequestDTO
                .builder()
                .attendanceRequestId(5L)
                .requestReason("휴직 사유 소멸")
                .endDate(LocalDate.now().plusDays(7).toString())
                .employeeId(1L)
                .attendanceRequestTypeId(6L)
                .attachments(attachments)
                .build();

        // When
        ResponseLeaveReturnRequestDTO resReturnRequestDTO =
                attendanceRequestService.registReturnRequest(reqRequestRequestDTO);
        if (resReturnRequestDTO != null) {
            log.info(resReturnRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resReturnRequestDTO);
    }

    @DisplayName("근태 신청 취소 테스트")
    @Test
    void testCancelAttendanceRequest() {
        // Given
        Long attendanceRequestId = 1L;
        RequestCancelAttendanceRequestDTO reqCancelAttendanceRequestDTO = RequestCancelAttendanceRequestDTO
                .builder()
                .cancelReason("잘못 신청했습니다.")
                .build();

        // When
        ResponseAttendanceRequestDTO resAttendanceRequestDTO =
                attendanceRequestService.cancelAttendanceRequest(attendanceRequestId, reqCancelAttendanceRequestDTO);
        if (resAttendanceRequestDTO != null) {
            log.info(resAttendanceRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resAttendanceRequestDTO);
    }

    @DisplayName("초과근무 연장 테스트")
    @Test
    void testExtendOvertimeRequest() {
        // Given
        Long attendanceRequestId = 3L;

        LocalDateTime now = LocalDateTime.now();
        int minute = now.getMinute();
        int roundedMinute = (minute < 30) ? 30 : 0;

        LocalDateTime endTime;

        // 만약 0분이 되면 시간을 1시간 뒤로 설정
        if (roundedMinute == 0) {
            endTime = now.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        } else {
            endTime = now.withMinute(roundedMinute).withSecond(0).withNano(0);
        }

        String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        RequestOvertimeExtensionDTO reqOvertimeExtensionDTO = RequestOvertimeExtensionDTO
                .builder()
                .endTime(formattedEndTime)
                .build();

        // When
        ResponseCommuteRequestDTO resCommuteRequestDTO =
                attendanceRequestService.extendOvertime(attendanceRequestId, reqOvertimeExtensionDTO);
        if (resCommuteRequestDTO != null) {
            log.info(resCommuteRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resCommuteRequestDTO);
    }

}
