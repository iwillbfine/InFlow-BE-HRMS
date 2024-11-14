package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.AttendanceRequestTypeDTO;
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
class AttendanceRequestTypeServiceTests {

    @Autowired
    private AttendanceRequestTypeService attendanceRequestTypeService;

    @DisplayName("근태신청유형 전체 조회 테스트")
    @Test
    void testFindAttendanceRequestTypes() {
        // Given
        List<AttendanceRequestTypeDTO> attendanceRequestTypes;

        // When
        attendanceRequestTypes = attendanceRequestTypeService.findAttendanceRequestTypes();

        if (attendanceRequestTypes != null && !attendanceRequestTypes.isEmpty()) {
            attendanceRequestTypes.forEach(x -> log.info(x.toString()));
        }

        // Then
        Assertions.assertNotNull(attendanceRequestTypes);
    }

}
