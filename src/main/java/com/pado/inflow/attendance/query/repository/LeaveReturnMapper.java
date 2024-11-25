package com.pado.inflow.attendance.query.repository;

import com.pado.inflow.attendance.query.dto.LeaveReturnDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveReturnMapper {

    // 사웝별 휴복직 내역 조회
    List<LeaveReturnDTO> findLeaveReturnsByEmployeeId(Long employeeId, Integer elementsPerPage, Integer offset);

    // 사웝별 휴복직 전체 개수 조회
    Integer getTotalLeaveReturnsByEmployeeId(Long employeeId);

    // 당일기준 휴복직 내역 조회
    LeaveReturnDTO findTodayLeaveByEmployeeId(Long employeeId);

}
