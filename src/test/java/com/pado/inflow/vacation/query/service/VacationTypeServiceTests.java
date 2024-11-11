package com.pado.inflow.vacation.query.service;

import com.pado.inflow.vacation.query.dto.VacationTypeDTO;
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
class VacationTypeServiceTests {

    @Autowired
    private VacationTypeService vacationTypeService;

    @DisplayName("휴가 종류 전체 조회 테스트")
    @Test
    void testFindVacationTypes() {
        // Given
        List<VacationTypeDTO> vacationTypes;

        // When
        vacationTypes = vacationTypeService.findVacationTypes();

        if (vacationTypes != null && !vacationTypes.isEmpty()) {
            vacationTypes.forEach(x -> log.info(x.toString()));
        }

        // Then
        Assertions.assertNotNull(vacationTypes);
    }

}
