package com.pado.inflow.attendance.command.application.controller;

import com.pado.inflow.attendance.query.service.CommuteQueryService;
import com.pado.inflow.attendance.command.application.service.CommuteCommandService;
import com.pado.inflow.attendance.query.service.LeaveReturnService;
import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.vacation.query.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("commandCommandController")
@RequestMapping("/api/commutes")
public class CommuteCommandController {


    private final CommuteQueryService commuteQueryService;
    private final CommuteCommandService commuteCommandService;
    private final LeaveReturnService leaveReturnService;
    private final VacationRequestService vacationRequestService;

    @Autowired
    public CommuteCommandController(CommuteQueryService commuteQueryService,
                                    CommuteCommandService commuteCommandService,
                                    LeaveReturnService leaveReturnService,
                                    VacationRequestService vacationRequestService
            ) {
        this.commuteQueryService =commuteQueryService;
        this.commuteCommandService=commuteCommandService;
        this.leaveReturnService=leaveReturnService;
        this.vacationRequestService=vacationRequestService;
    }

    //설명. 사원별 출퇴근 이력을 조회하고, 업데이트 하는 API
    @PostMapping("/")
    public ResponseDTO<String> checkAndUpdateCommute(@RequestParam Long employeeId, @RequestParam Boolean isRemote) {
        return ResponseDTO.ok("성공");
        
                
    }

}
