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

}
