package com.pado.inflow.vacation.query.controller;

import com.pado.inflow.vacation.query.dto.PageDTO;
import com.pado.inflow.vacation.query.dto.VacationDTO;
import com.pado.inflow.vacation.query.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryVacationController")
@RequestMapping("/api/vacations")
public class VacationController {

    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    // 사원별 잔여 휴가 조회
    @GetMapping("/left")
    public PageDTO<VacationDTO> getLeftVacationsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                             @RequestParam("page") Integer pageNo) {
        return vacationService.findLeftVacationsByEmployeeId(employeeId, pageNo);
    }

    // 사원별 사용 휴가 조회
    @GetMapping("/used")
    public PageDTO<VacationDTO> getUsedVacationsByEmployeeId(@RequestParam("eid") Long employeeId,
                                                             @RequestParam("page") Integer pageNo) {
        return vacationService.findUsedVacationsByEmployeeId(employeeId, pageNo);
    }

}
