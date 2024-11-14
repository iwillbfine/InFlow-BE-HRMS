package com.pado.inflow.attendance.query.service;

import com.pado.inflow.attendance.query.dto.BusinessTripDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.repository.BusinessTripMapper;
import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTripServiceImpl implements BusinessTripService {

    private final Integer PAGE_SIZE = 10; // 페이지 간격
    private final Integer ELEMENTS_PER_PAGE = 10; // 한 페이지 당 요소 개수

    private final BusinessTripMapper businessTripMapper;

    public BusinessTripServiceImpl(BusinessTripMapper businessTripMapper) {
        this.businessTripMapper = businessTripMapper;
    }

    // 사원별 출장파견 내역 조회
    @Override
    public PageDTO<BusinessTripDTO> findBusinessTripsByEmployeeId(Long employeeId, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        Integer totalElements = businessTripMapper.getTotalBusinessTripsByEmployeeId(employeeId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_BUSINESS_TRIP);
        }

        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<BusinessTripDTO> businessTrips =
                businessTripMapper.findBusinessTripsByEmployeeId(employeeId, ELEMENTS_PER_PAGE, offset);
        if (businessTrips == null || businessTrips.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_BUSINESS_TRIP);
        }

        return new PageDTO<>(businessTrips, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

}
