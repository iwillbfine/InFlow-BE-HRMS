package com.pado.inflow.payroll.command.application.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.command.application.dto.RequestIrregularAllowanceDTO;
import com.pado.inflow.payroll.command.application.dto.ResponseIrregularAllowanceDTO;
import com.pado.inflow.payroll.command.application.service.IrregularAllowanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commandIrregularAllowanceController")
@RequestMapping("/api/irregular-allowances")
public class IrregularAllowanceController {

    private final IrregularAllowanceService irregularAllowanceService;

    @Autowired
    public IrregularAllowanceController(IrregularAllowanceService irregularAllowanceService) {
        this.irregularAllowanceService = irregularAllowanceService;
    }

    @PostMapping
    public ResponseDTO<?> createIrregularAllowance(@RequestBody RequestIrregularAllowanceDTO reqAllowanceDTO) {
        ResponseIrregularAllowanceDTO resAllowanceDTO = irregularAllowanceService.createIrregularAllowance(reqAllowanceDTO);
        return ResponseDTO.ok(resAllowanceDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO<?> updateIrregularAllowance(@PathVariable("id") Long irregularAllowanceId,
                                                   @RequestBody RequestIrregularAllowanceDTO reqAllowanceDTO) {
        ResponseIrregularAllowanceDTO resAllowanceDTO =
                irregularAllowanceService.updateIrregularAllowance(irregularAllowanceId, reqAllowanceDTO);
        return ResponseDTO.ok(resAllowanceDTO);
    }
}
