package com.pado.inflow.attendance.query.repository;

import com.pado.inflow.attendance.query.dto.CommuteDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CommuteMapper {

    // 사원별 출퇴근 내역 조회
    List<CommuteDTO> findCommutesByEmployeeId(@Param("employeeId") Long employeeId,
                                              @Param("date") LocalDate date);

    // 사원별 초과근무 내역 조회
    List<CommuteDTO> findOvertimesByEmployeeId(@Param("employeeId") Long employeeId,
                                               @Param("date") LocalDate date);

    // 당일 재택 출퇴근 내역 조회
    CommuteDTO findTodayRemoteByEmployeeId(Long employeeId);

    // 당일 출퇴근 내역 조회
    CommuteDTO findTodayCommuteByEmployeeId(Long employeeId);

}
