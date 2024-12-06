//package com.pado.inflow.vacation.query.service;
//
//import com.pado.inflow.vacation.query.dto.VacationPolicyDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class VacationPolicyServiceTests {
//
//    @Autowired
//    private VacationPolicyService vacationPolicyService;
//
//    @DisplayName("연도별 휴가 정책 조회 테스트")
//    @Test
//    void testFindVacationPoliciesByYear() {
//        // Given
//        Integer year = 2024;
//
//        // When
//        List<VacationPolicyDTO> vacationPolicies = vacationPolicyService.findVacationPoliciesByYear(year);
//
//        if (vacationPolicies != null && !vacationPolicies.isEmpty()) {
//            vacationPolicies.forEach(x -> log.info(x.toString()));
//        }
//
//        // Then
//        Assertions.assertNotNull(vacationPolicies);
//    }
//
//}
