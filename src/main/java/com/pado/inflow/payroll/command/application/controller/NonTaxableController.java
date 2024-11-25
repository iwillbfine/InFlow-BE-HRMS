package com.pado.inflow.payroll.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.command.application.dto.RequestNonTaxableDTO;
import com.pado.inflow.payroll.command.application.dto.ResponseNonTaxableDTO;
import com.pado.inflow.payroll.command.application.service.NonTaxableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("commandNonTaxableController")
@RequestMapping("/api/non-taxable-payrolls")
public class NonTaxableController {

    private final NonTaxableService nonTaxableService;

    @Autowired
    public NonTaxableController(NonTaxableService nonTaxableService) {
        this.nonTaxableService = nonTaxableService;
    }

    // 비정기 수당 항목 입력
    @PostMapping
    public ResponseDTO<?> createNonTaxable(@RequestBody RequestNonTaxableDTO reqNonTaxableDTO) {
        ResponseNonTaxableDTO resNonTaxableDTO = nonTaxableService.createNonTaxable(reqNonTaxableDTO);
        return ResponseDTO.ok(resNonTaxableDTO);
    }

}
