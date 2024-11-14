package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.BusinessTripDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
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
class BusinessTripServiceTests {

    @Autowired
    private BusinessTripService businessTripService;

    @DisplayName("사원별 출장파견 내역 조회 테스트")
    @Test
    void testFindBusinessTripsByEmployeeId() {
        // Given
        Long employeeId = 4L;
        Integer pageNo = 1;

        // When
        PageDTO<BusinessTripDTO> businessTrips = businessTripService.findBusinessTripsByEmployeeId(employeeId, pageNo);
        if (businessTrips != null) {
            log.info(businessTrips.toString());
        }

        // Then
        Assertions.assertNotNull(businessTrips);
    }

}
