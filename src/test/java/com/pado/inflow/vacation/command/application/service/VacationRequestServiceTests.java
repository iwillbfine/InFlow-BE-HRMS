package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.vacation.command.application.dto.RequestCancelVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.RequestVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
@Transactional
class VacationRequestServiceTests {

    @Autowired
    private VacationRequestService vacationRequestService;

    @DisplayName("휴가 신청 등록 테스트")
    @Test
    void testRegistVacationRequest() {
        // Given
        RequestVacationRequestDTO reqVacationRequestDTO = RequestVacationRequestDTO
                .builder()
                .startDate("2024-11-13")
                .endDate("2024-11-15")
                .requestReason("가족 여행")
                .employeeId(1L)
                .vacationId(1L)
                .build();

        // When
        ResponseVacationRequestDTO resVacationRequestDTO = vacationRequestService.registVacationRequest(reqVacationRequestDTO, null);
        if (resVacationRequestDTO != null) {
            log.info(resVacationRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resVacationRequestDTO);
    }

    @DisplayName("휴가 신청 취소 테스트")
    @Test
    void testCancelVacationRequest() {
        // Given
        Long vacationRequestId = 1L;
        RequestCancelVacationRequestDTO reqCancelVacationRequestDTO = RequestCancelVacationRequestDTO
                .builder()
                .cancelReason("날짜를 잘못 기입하였음.")
                .build();

        // When
        ResponseVacationRequestDTO resVacationRequestDTO =
                vacationRequestService.cancelVacationRequest(vacationRequestId, reqCancelVacationRequestDTO);
        if (resVacationRequestDTO != null) {
            log.info(resVacationRequestDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resVacationRequestDTO);
    }
}
