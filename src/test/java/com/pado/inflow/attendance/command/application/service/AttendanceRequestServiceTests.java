package com.pado.inflow.attendance.command.application.service;

import com.pado.inflow.attendance.command.application.dto.RequestCommuteRequestDTO;
import com.pado.inflow.attendance.command.application.dto.ResponseCommuteRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                .requestReason("금요일은 재택근무 하는 날")
                .startDate("2024-11-15")
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
        LocalDateTime now = LocalDateTime.now().withNano(0);

        int minute = now.getMinute();
        int roundedMinute = (minute < 30) ? 30 : 0;

        LocalDateTime startTime;

        // 만약 0분이 되면 시간을 1시간 뒤로 설정
        if (roundedMinute == 0) {
            startTime = now.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        } else {
            startTime = now.withMinute(roundedMinute).withSecond(0).withNano(0);
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

}
