package com.pado.inflow.attendance.query.controller;

import com.pado.inflow.attendance.query.dto.LeaveReturnDTO;
import com.pado.inflow.attendance.query.dto.PageDTO;
import com.pado.inflow.attendance.query.service.LeaveReturnService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryLeaveReturnController")
@RequestMapping("/api/leave-returns")
public class LeaveReturnController {

    private final LeaveReturnService leaveReturnService;

    @Autowired
    public LeaveReturnController(LeaveReturnService leaveReturnService) {
        this.leaveReturnService = leaveReturnService;
    }

    // 사웝별 휴복직 내역 조회
    @GetMapping
    public ResponseDTO<?> getLeaveReturnsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                      @RequestParam("page") Integer pageNo) {
        PageDTO<LeaveReturnDTO> leaveReturns =
                leaveReturnService.findLeaveReturnsByEmployeeId(employeeId, pageNo);
        return ResponseDTO.ok(leaveReturns);
    }

}
