package com.pado.inflow.department.query.repository;

import com.pado.inflow.department.query.dto.CompanyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {

    // 회사 정보를 가져오기
    CompanyDTO getCompanyInfo();
}
