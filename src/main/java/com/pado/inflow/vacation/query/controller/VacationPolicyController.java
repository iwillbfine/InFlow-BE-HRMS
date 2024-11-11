package com.pado.inflow.vacation.query.controller;

import com.pado.inflow.vacation.query.dto.VacationPolicyDTO;
import com.pado.inflow.vacation.query.service.VacationPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryVacationPolicyController")
@RequestMapping("/api/vacation/policies")
public class VacationPolicyController {

    private final VacationPolicyService vacationPolicyService;

    @Autowired
    public VacationPolicyController(VacationPolicyService vacationPolicyService) {
        this.vacationPolicyService = vacationPolicyService;
    }

    @GetMapping
    public List<VacationPolicyDTO> getVacationPoliciesByYear(@RequestParam("year") Integer year) {
        return vacationPolicyService.findVacationPoliciesByYear(year);
    }

}
