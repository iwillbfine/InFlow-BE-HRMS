package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.vacation.command.application.dto.RequestVacationPolicyDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationPolicyDTO;
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
class VacationPolicyServiceTests {

    @Autowired
    private VacationPolicyService vacationPolicyService;

    @DisplayName("휴가 정책 등록 테스트")
    @Test
    void testRegistVacationPolicy() {
        // Given
        RequestVacationPolicyDTO reqVacationPolicyDTO = RequestVacationPolicyDTO
                .builder()
                .vacationPolicyName("테스트용 정책")
                .allocationDays(3L)
                .paidStatus("Y")
                .year(2024)
                .autoAllocationCycle("0 0 * * *")
                .vacationTypeId(4L)
                .policyRegisterId(1L)
                .build();

        // When
        ResponseVacationPolicyDTO resVacationPolicyDTO = vacationPolicyService.registVacationPolicy(reqVacationPolicyDTO);
        if (resVacationPolicyDTO != null) {
            log.info(resVacationPolicyDTO.toString());
        }

        // Then
        Assertions.assertNotNull(reqVacationPolicyDTO);
    }

}
