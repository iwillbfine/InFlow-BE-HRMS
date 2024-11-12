package com.pado.inflow.vacation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.vacation.command.application.dto.RequestVacationDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationDTO;
import com.pado.inflow.vacation.command.application.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("commandVacationController")
@RequestMapping("/api/vacations")
public class VacationController {

    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    // 휴가 지급
    @PostMapping
    public ResponseDTO<?> createVacation(@RequestBody RequestVacationDTO reqVacationDTO) {
        ResponseVacationDTO resVacationDTO = vacationService.registVacation(reqVacationDTO);
        return ResponseDTO.ok(resVacationDTO);
    }

}
