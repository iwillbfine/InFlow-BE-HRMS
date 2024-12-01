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
    List<VacationDTO> findUsedVacationsByEmployeeId(@Param("employeeId") Long employeeId,
                                                    @Param("elementsPerPage") Integer elementsPerPage,
                                                    @Param("offset") Integer offset);

    // 사원별 잔여 휴가 내역 개수 조회
    Integer getTotalLeftVacationsByEmployeeId(Long employeeId);

    // 사원별 사용 휴가 내역 개수 조회
    Integer getTotalUsedVacationsByEmployeeId(Long employeeId);

    // 사원별 잔여 휴가 전체 조회
    List<VacationDTO> findLeftAllVacationsByEmployeeId(Long employeeId);

}
