package com.pado.inflow.attendance.query.controller;

import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.dto.ResponseCommuteDTO;
import com.pado.inflow.attendance.query.service.CommuteQueryService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryCommuteController")
@RequestMapping("/api/commutes")
public class CommuteController {

    private final CommuteQueryService commuteService;

    @Autowired
    public CommuteController(CommuteQueryService commuteService) {
        this.commuteService = commuteService;
    }

    // 사원별 출퇴근 내역 조회
    @GetMapping
    public ResponseDTO<?> getCommutesByEmployeeId(@RequestParam("eid") Long employeeId,
                                                  @RequestParam("page") Integer pageNo,
                                                  @RequestParam("date") String date) {
        PageDTO<ResponseCommuteDTO> commutes = commuteService.findCommutesByEmployeeId(employeeId, pageNo, date);
        return ResponseDTO.ok(commutes);
    }

}
