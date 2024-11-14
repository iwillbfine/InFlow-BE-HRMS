package com.pado.inflow.attendance.query.controller;

import com.pado.inflow.attendance.query.dto.BusinessTripDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.service.BusinessTripService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryBusinessTripController")
@RequestMapping("/api/business-trips")
public class BusinessTripController {

    private final BusinessTripService businessTripService;

    @Autowired
    public BusinessTripController(BusinessTripService businessTripService) {
        this.businessTripService = businessTripService;
    }

    // 사원별 출장파견 내역 조회
    @GetMapping
    public ResponseDTO<?> getBusinessTripsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                       @RequestParam("page") Integer pageNo) {
        PageDTO<BusinessTripDTO> businessTrips = businessTripService.findBusinessTripsByEmployeeId(employeeId,pageNo);
        return ResponseDTO.ok(businessTrips);
    }

}
