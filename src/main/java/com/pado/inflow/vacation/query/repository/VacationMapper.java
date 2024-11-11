package com.pado.inflow.vacation.query.repository;

import com.pado.inflow.vacation.query.dto.VacationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VacationMapper {

    // 사원별 잔여 휴가 조회
    List<VacationDTO> findLeftVacationsByEmployeeId(@Param("employeeId") Long employeeId,
                                                   @Param("elementsPerPage") Integer elementsPerPage,
                                                   @Param("offset") Integer offset);

    // 사원별 사용 휴가 조회


    // 사원별 잔여 휴가 개수 조회
    Integer getTotalLeftVacationsByEmployeeId(Long employeeId);

}
