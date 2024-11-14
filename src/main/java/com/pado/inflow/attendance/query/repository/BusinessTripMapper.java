package com.pado.inflow.attendance.query.repository;

import com.pado.inflow.attendance.query.dto.BusinessTripDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessTripMapper {

    // 사원별 출장파견 내역 조회
    List<BusinessTripDTO> findBusinessTripsByEmployeeId(Long employeeId, Integer elementsPerPage, Integer offset);

    // 사원별 출장파견 전체 개수 조회
    Integer getTotalBusinessTripsByEmployeeId(Long employeeId);

}
