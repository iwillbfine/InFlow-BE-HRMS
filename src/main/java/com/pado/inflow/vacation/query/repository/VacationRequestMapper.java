package com.pado.inflow.vacation.query.repository;

import com.pado.inflow.vacation.query.dto.VacationRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VacationRequestMapper {

    // 사원별 휴가 신청 내역 미리보기 조회
    List<VacationRequestDTO> findVacationRequestPreviewsByEmployeeId(Long employeeId);

    // 사원별 휴가 신청 내역 전체 조회
    List<VacationRequestDTO> findVacationRequestsByEmployeeId(@Param("employeeId") Long employeeId,
                                                              @Param("elementsPerPage") Integer elementsPerPage,
                                                              @Param("offset") Integer offset);

    // 사원별 휴가 신청 내역 전체 개수 조회
    Integer getTotalVacationRequestsByEmployeeId(Long employeeId);

    // 당일기준 휴가 내역 조회
    VacationRequestDTO findTodayVacationByEmployeeId(Long employeeId);

}
