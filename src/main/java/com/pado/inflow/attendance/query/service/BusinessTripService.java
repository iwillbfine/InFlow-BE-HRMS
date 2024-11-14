package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.BusinessTripDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;

public interface BusinessTripService {

    // 사원별 출장파견 내역 조회
    PageDTO<BusinessTripDTO> findBusinessTripsByEmployeeId(Long employeeId, Integer pageNo);

}
