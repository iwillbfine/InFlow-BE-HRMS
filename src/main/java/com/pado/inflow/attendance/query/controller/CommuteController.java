package com.pado.inflow.attendance.query.controller;

import com.pado.inflow.attendance.query.dto.CommuteDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.service.CommuteService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryCommuteController")
@RequestMapping("/api/commutes")
public class CommuteController {

    private final CommuteService commuteService;

    @Autowired
    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }

    // 사원별 출퇴근 내역 조회
    @GetMapping
    public ResponseDTO<?> getCommutesByEmployeeId(@RequestParam("eid") Long employeeId,
                                                  @RequestParam("page") Integer pageNo) {
        PageDTO<CommuteDTO> commutes = commuteService.findCommutesByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(commutes);
    }

}
