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

import java.util.List;

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
        Integer pageNo = 1;

        // When
        PageDTO<VacationDTO> vacations = vacationService.findLeftVacationsByEmployeeId(employeeId, pageNo);
        if (vacations != null) {
            log.info(vacations.toString());
        }

        // Then
        Assertions.assertNotNull(vacations);
    }

    @DisplayName("사원별 사용 휴가 조회 테스트")
    @Test
    void testFindUsedVacationsByEmployeeId() {
        // Given
        Long employeeId = 4L;
        Integer pageNo = 1;

        // When
        PageDTO<VacationDTO> vacations = vacationService.findUsedVacationsByEmployeeId(employeeId, pageNo);
        if (vacations != null) {
            log.info(vacations.toString());
        }

        // Then
        Assertions.assertNotNull(vacations);
    }

    @DisplayName("사원별 잔여 휴가 전체 조회 테스트")
    @Test
    void testFindLeftAllVacationsByEmployeeId() {
        // Given
        Long employeeId = 4L;

        // When
        List<VacationDTO> vacations = vacationService.findLeftAllVacationsByEmployeeId(employeeId);
        if (vacations != null && !vacations.isEmpty()) {
            log.info(vacations.toString());
        }

        // Then
        Assertions.assertNotNull(vacations);
    }

}
