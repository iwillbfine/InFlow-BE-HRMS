package com.pado.inflow.vacation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.vacation.query.dto.PageDTO;
import com.pado.inflow.vacation.query.dto.VacationRequestDTO;
import com.pado.inflow.vacation.query.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryVacationRequestController")
@RequestMapping("/api/vacation-requests")
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;

    @Autowired
    public VacationRequestController(VacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    // 사원별 휴가 신청 미리보기 조회
    @GetMapping("/preview")
    public ResponseDTO<?> getVacationRequestPreviewsByEmployeeId(@RequestParam("eid") Long employeeId) {
        List<VacationRequestDTO> vacationRequestPreviews = vacationRequestService.findVacationRequestPreviewsByEmployeeId(employeeId);
        return ResponseDTO.ok(vacationRequestPreviews);
    }

    // 사원별 휴가 신청 조회
    @GetMapping
    public ResponseDTO<?> getVacationRequestsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                          @RequestParam("page") Integer pageNo,
                                                          @RequestParam("date") String date) {
        PageDTO<VacationRequestDTO> vacationRequest = vacationRequestService.findVacationRequestsByEmployeeId(employeeId, pageNo, date);
        return ResponseDTO.ok(vacationRequest);
    }

}
