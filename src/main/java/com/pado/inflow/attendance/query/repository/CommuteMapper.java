package com.pado.inflow.attendance.query.repository;

import com.pado.inflow.attendance.query.dto.CommuteDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommuteMapper {

    // 사원별 출퇴근 내역 조회
    List<CommuteDTO> findCommutesByEmployeeId(@Param("employeeId") Long employeeId,
                                              @Param("elementsPerPage") Integer elementsPerPage,
                                              @Param("offset") Integer offset);

    // 사원별 출퇴근 내역 전체 개수 조회
    Integer getTotalCommutesByEmployeeId(Long employeeId);

}
