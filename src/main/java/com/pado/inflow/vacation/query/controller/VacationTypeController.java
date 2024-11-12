package com.pado.inflow.vacation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.vacation.query.dto.VacationTypeDTO;
import com.pado.inflow.vacation.query.service.VacationTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryVacationTypeController")
@RequestMapping("/api/vacation/types")
public class VacationTypeController {

    private final VacationTypeService vacationTypeService;

    public VacationTypeController(VacationTypeService vacationTypeService) {
        this.vacationTypeService = vacationTypeService;
    }

    // 휴가 종류 전체 조회
    @GetMapping
    public ResponseDTO<?> getVacationTypes() {
        List<VacationTypeDTO> vacationTypes = vacationTypeService.findVacationTypes();
        return ResponseDTO.ok(vacationTypes);
    }

}
