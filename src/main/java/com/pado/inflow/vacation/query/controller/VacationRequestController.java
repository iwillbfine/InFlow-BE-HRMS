package com.pado.inflow.vacation.query.controller;

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
@RequestMapping("/api/vacation/requests")
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;

    @Autowired
    public VacationRequestController(VacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    @GetMapping("/preview")
    public List<VacationRequestDTO> getVacationRequestPreviewsByEmployeeId(@RequestParam("eid") Long employeeId) {
        return vacationRequestService.findVacationRequestPreviewsByEmployeeId(employeeId);
    }

    @GetMapping
    public PageDTO<VacationRequestDTO> getVacationRequestsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                                       @RequestParam("page") Integer pageNo) {
        return vacationRequestService.findVacationRequestsByEmployeeId(employeeId, pageNo);
    }

}
