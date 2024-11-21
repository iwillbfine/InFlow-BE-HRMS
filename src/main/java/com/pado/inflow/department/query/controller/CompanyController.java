package com.pado.inflow.department.query.controller;

import com.pado.inflow.department.query.dto.CompanyDTO;
import com.pado.inflow.department.query.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // 회사 정보를 가져오는 API (단일 회사)
    @GetMapping
    public CompanyDTO getCompanyInfo() {
        return companyService.getCompanyInfo();
    }
}
