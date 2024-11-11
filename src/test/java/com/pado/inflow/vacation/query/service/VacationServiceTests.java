package com.pado.inflow.vacation.query.service;

import com.pado.inflow.vacation.query.dto.PageDTO;
import com.pado.inflow.vacation.query.dto.VacationDTO;
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

    @DisplayName("사원별 잔여 휴가 조회 테스트")
    @Test
    void testFindLeftVacationsByEmployeeId() {
        // Given
        Long employeeId = 4L;

        // When
        PageDTO<VacationDTO> vacations = vacationService.findLeftVacationsByEmployeeId(employeeId, 1);
        log.info(vacations.toString());

        // Then
        Assertions.assertNotNull(vacations.getElements());
    }

}
