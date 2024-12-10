//package com.pado.inflow.attendance.query.service;
//
//import com.pado.inflow.attendance.query.dto.ResponseCommuteDTO;
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
//class CommuteServiceTests {
//
//    @Autowired
//    private CommuteQueryService commuteService;
//
//    @DisplayName("사원별 출퇴근 내역 조회 테스트")
//    @Test
//    void testFindCommutesByEmployeeId() {
//        // Given
//        Long employeeId = 1L;
//        String date = "2024-11";
//        // When
//        List<ResponseCommuteDTO> commutes = commuteService.findCommutesByEmployeeId(employeeId, date);
//        if (commutes != null) {
//            log.info(commutes.toString());
//        }
//
//        // Then
//        Assertions.assertNotNull(commutes);
//    }
//
//}
