package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.vacation.command.application.dto.RequestVacationDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationDTO;
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
class VacationServiceTests {

    @Autowired
    private VacationService vacationService;

    @DisplayName("휴가 지급 테스트")
    @Test
    void testRegistVacation() {
        // Given
        RequestVacationDTO reqVacationDTO = RequestVacationDTO
                .builder()
                .expiredAt("2024-11-15")
                .employeeId(1L)
                .vacationPolicyId(4L)
                .build();

        // When
        ResponseVacationDTO resVacationDTO = vacationService.registVacation(reqVacationDTO);
        if (resVacationDTO != null) {
            log.info(resVacationDTO.toString());
        }

        // Then
        Assertions.assertNotNull(resVacationDTO);
    }

}
