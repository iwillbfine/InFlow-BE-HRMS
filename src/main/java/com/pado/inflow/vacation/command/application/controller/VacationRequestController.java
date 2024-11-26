package com.pado.inflow.vacation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.vacation.command.application.dto.RequestCancelVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.RequestVacationRequestDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationRequestDTO;
import com.pado.inflow.vacation.command.application.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseDTO<?> createVacationRequest(@RequestParam("start_date") String startDate,
                                                @RequestParam("end_date") String endDate,
                                                @RequestParam("request_reason") String requestReason,
                                                @RequestParam("employee_id") Long employeeId,
                                                @RequestParam("vacation_id") Long vacationId,
                                                @RequestParam(value = "attachments", required = false) List<MultipartFile> attachments) {

        // DTO 객체 생성
        RequestVacationRequestDTO reqVacationRequestDTO = RequestVacationRequestDTO
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .requestReason(requestReason)
                .employeeId(employeeId)
                .vacationId(vacationId)
                .attachments(attachments)
                .build();

        // 서비스 호출
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
