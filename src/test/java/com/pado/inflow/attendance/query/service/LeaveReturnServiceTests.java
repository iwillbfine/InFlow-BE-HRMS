package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.LeaveReturnDTO;
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
class LeaveReturnServiceTests {

    @Autowired
    private LeaveReturnService leaveReturnService;

    @DisplayName("사원별 휴복직 조회 테스트")
    @Test
    void testFindLeaveReturnsByEmployeeId() {
        // Given
        Long employeeId = 1L;
        Integer pageNo = 1;

        // When
        PageDTO<LeaveReturnDTO> leaveReturns = leaveReturnService.findLeaveReturnsByEmployeeId(employeeId, pageNo);
        if (leaveReturns != null) {
            log.info(leaveReturns.toString());
        }

        // Then
        Assertions.assertNotNull(leaveReturns);
    }

}
