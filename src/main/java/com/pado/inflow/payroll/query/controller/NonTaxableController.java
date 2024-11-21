package com.pado.inflow.payroll.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.payroll.query.dto.NonTaxableDTO;
import com.pado.inflow.payroll.query.dto.PageDTO;
import com.pado.inflow.payroll.query.service.NonTaxableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryNonTaxableController")
@RequestMapping("api/non-taxable-payrolls")
public class NonTaxableController {

    private final NonTaxableService nonTaxableService;

    @Autowired
    public NonTaxableController(NonTaxableService nonTaxableService) {
        this.nonTaxableService = nonTaxableService;
    }

    // 비과세 급여 항목 전체 조회
    @GetMapping
    public ResponseDTO<?> getNonTaxablePayrolls(@RequestParam(value = "page", defaultValue = "1") Integer pageNo) {

        PageDTO<NonTaxableDTO> nonTaxableDTOs = nonTaxableService.findAllNonTaxablePayrolls(pageNo);
        return ResponseDTO.ok(nonTaxableDTOs);
    }
}
