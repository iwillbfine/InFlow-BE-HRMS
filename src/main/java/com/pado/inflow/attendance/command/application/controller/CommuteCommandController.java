package com.pado.inflow.attendance.command.application.controller;

import com.pado.inflow.attendance.command.application.dto.ResponseCommuteDTO;
import com.pado.inflow.attendance.command.application.service.CommuteCommandService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("commandCommandController")
@RequestMapping("/api/commutes")
public class CommuteCommandController {

    private final CommuteCommandService commuteCommandService;

    @Autowired
    public CommuteCommandController(CommuteCommandService commuteCommandService) {
        this.commuteCommandService=commuteCommandService;
    }

    //설명. 사원별 출퇴근 이력을 조회하고, 업데이트 하는 API
    @PostMapping
    public ResponseDTO<?> checkAndUpdateCommute(@RequestParam("eid") Long employeeId) {
        String commuteStatus = commuteCommandService.checkAndUpdateCommute(employeeId);
        return ResponseDTO.ok(commuteStatus);
    }

}
