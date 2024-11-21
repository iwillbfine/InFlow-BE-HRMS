package com.pado.inflow.department.query.service;

import com.pado.inflow.department.query.dto.CompanyDTO;
import com.pado.inflow.department.query.repository.CompanyMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    // 회사 정보를 가져오는 메서드
    public CompanyDTO getCompanyInfo() {
        return companyMapper.getCompanyInfo();
    }
}
