package com.pado.inflow.payroll.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.query.dto.IrregularAllowanceDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;
import com.pado.inflow.payroll.query.service.IrregularAllowanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryIrregularAllowanceController")
@RequestMapping("/api/irregular-allowances")
public class IrregularAllowanceController {

    private IrregularAllowanceService irregularAllowanceService;

    @Autowired
    public IrregularAllowanceController(IrregularAllowanceService irregularAllowanceService) {
        this.irregularAllowanceService = irregularAllowanceService;
    }

    // 비정기 수당 항목 전체 조회
    @GetMapping
    public ResponseDTO<?> getIrregularAllowances(@RequestParam(value = "page", defaultValue = "1") Integer pageNo) {
        PageDTO<IrregularAllowanceDTO> irregularAllowances = irregularAllowanceService.findAllIrregularAllowances(pageNo);
        return ResponseDTO.ok(irregularAllowances);
    }



}
