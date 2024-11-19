package com.pado.inflow.vacation.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.vacation.command.application.dto.RequestVacationPolicyDTO;
import com.pado.inflow.vacation.command.application.service.VacationPolicyService;
import org.springframework.web.bind.annotation.*;

@RestController("commandVacationPolicyController")
@RequestMapping("/api/vacation-policies")
public class VacationPolicyController {

    private final VacationPolicyService vacationPolicyService;

    public VacationPolicyController(VacationPolicyService vacationPolicyService) {
        this.vacationPolicyService = vacationPolicyService;
    }

    // 휴가 정책 등록
    @PostMapping
    public ResponseDTO<?> createVacationPolicy(@RequestBody RequestVacationPolicyDTO reqVacationPolicyDTO) {
        return ResponseDTO.ok(vacationPolicyService.registVacationPolicy(reqVacationPolicyDTO));
    }

    // 휴가 정책 수정
    @PutMapping("/{vacationPolicyId}")
    public ResponseDTO<?> updateVacationPolicy(@PathVariable("vacationPolicyId") Long vacationPolicyId,
                                               @RequestBody RequestVacationPolicyDTO reqVacationPolicyDTO) {
        return ResponseDTO.ok(vacationPolicyService.modifyVacationPolicy(vacationPolicyId, reqVacationPolicyDTO));
    }

}
