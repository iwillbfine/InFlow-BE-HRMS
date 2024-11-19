package com.pado.inflow.vacation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.vacation.command.application.dto.RequestCancelVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.RequestVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationRequestDTO;
import com.pado.inflow.vacation.command.application.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandVacationRequestController")
@RequestMapping("/api/vacation-requests")
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;

    @Autowired
    public VacationRequestController(VacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    // 휴가 신청 등록
    @PostMapping
    public ResponseDTO<?> createVacationRequest(@RequestBody RequestVacationRequestDTO reqVacationRequestDTO) {
        ResponseVacationRequestDTO resVacationRequestDTO =
                vacationRequestService.registVacationRequest(reqVacationRequestDTO);
        return ResponseDTO.ok(resVacationRequestDTO);
    }

    // 휴가 신청 취소
    @PatchMapping("/{vacationRequestId}")
    public ResponseDTO<?> cancelVacationRequest(@PathVariable("vacationRequestId") Long vacationRequestId,
                                                @RequestBody RequestCancelVacationRequestDTO reqCancelVacationRequestDTO) {
        ResponseVacationRequestDTO resVacationRequestDTO =
                vacationRequestService.cancelVacationRequest(vacationRequestId, reqCancelVacationRequestDTO);
        return ResponseDTO.ok(resVacationRequestDTO);
    }

}
