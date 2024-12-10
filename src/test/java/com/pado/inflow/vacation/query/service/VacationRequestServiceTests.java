//package com.pado.inflow.vacation.query.service;
//
//import com.pado.inflow.vacation.query.dto.PageDTO;
//import com.pado.inflow.vacation.query.dto.VacationRequestDTO;
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
//class VacationRequestServiceTests {
//
//    @Autowired
//    private VacationRequestService vacationRequestService;
//
//    @DisplayName("사원별 휴가 신청 내역 미리보기 테스트")
//    @Test
//    void testFindVacationRequestPreviewsByEmployeeId() {
//        // Given
//        Long employeeId = 1L;
//
//        // When
//        List<VacationRequestDTO> vacationRequestPreviews =
//                vacationRequestService.findVacationRequestPreviewsByEmployeeId(employeeId);
//        if (vacationRequestPreviews != null && !vacationRequestPreviews.isEmpty()) {
//            vacationRequestPreviews.forEach(x -> log.info(x.toString()));
//        }
//
//        // Then
//        Assertions.assertNotNull(vacationRequestPreviews);
//    }
//
//    @DisplayName("사원별 휴가 신청 내역 전체 조회 테스트")
//    @Test
//    void testFindVacationRequestsByEmployeeId() {
//        // Given
//        Long employeeId = 1L;
//        Integer pageNo = 1;
//        String date = "2024-11";
//
//        // When
//        PageDTO<VacationRequestDTO> vacationRequests =
//                vacationRequestService.findVacationRequestsByEmployeeId(employeeId, pageNo, date);
//        if (vacationRequests != null) {
//            log.info(vacationRequests.toString());
//        }
//
//        // Then
//        Assertions.assertNotNull(vacationRequests);
//    }
//
//}
