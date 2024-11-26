package com.pado.inflow.payroll.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.command.application.dto.RequestNonTaxableDTO;
import com.pado.inflow.payroll.command.application.dto.ResponseNonTaxableDTO;
import com.pado.inflow.payroll.command.application.service.NonTaxableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandNonTaxableController")
@RequestMapping("/api/non-taxable-payrolls")
public class NonTaxableController {

    private final NonTaxableService nonTaxableService;

    @Autowired
    public NonTaxableController(NonTaxableService nonTaxableService) {
        this.nonTaxableService = nonTaxableService;
    }

    // 비과세 수당 항목 입력
    @PostMapping
    public ResponseDTO<?> createNonTaxable(@RequestBody RequestNonTaxableDTO reqNonTaxableDTO) {
        ResponseNonTaxableDTO resNonTaxableDTO = nonTaxableService.createNonTaxable(reqNonTaxableDTO);
        return ResponseDTO.ok(resNonTaxableDTO);
    }

    // 비과세 수당 항목 수정
    @PutMapping("/{id}")
    public ResponseDTO<?> updateNonTaxable(@PathVariable("id") Long nonTaxableId, @RequestBody RequestNonTaxableDTO reqNonTaxableDTO) {
        ResponseNonTaxableDTO resNonTaxableDTO = nonTaxableService.updateNonTaxable(nonTaxableId, reqNonTaxableDTO);
        return ResponseDTO.ok(resNonTaxableDTO);
    }

}
