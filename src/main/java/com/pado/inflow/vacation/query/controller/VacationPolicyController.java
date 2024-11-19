package com.pado.inflow.vacation.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.vacation.query.dto.VacationPolicyDTO;
import com.pado.inflow.vacation.query.service.VacationPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("queryVacationPolicyController")
@RequestMapping("/api/vacation-policies")
public class VacationPolicyController {

    private final VacationPolicyService vacationPolicyService;

    @Autowired
    public VacationPolicyController(VacationPolicyService vacationPolicyService) {
        this.vacationPolicyService = vacationPolicyService;
    }

    // 연도별 휴가 정책 조회
    @GetMapping
    public ResponseDTO<?> getVacationPoliciesByYear(@RequestParam("year") Integer year) {
        List<VacationPolicyDTO> vacationPolicies = vacationPolicyService.findVacationPoliciesByYear(year);
        return ResponseDTO.ok(vacationPolicies);
    }

}
