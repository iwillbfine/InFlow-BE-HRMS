package com.pado.inflow.payroll.query.repository;

import com.pado.inflow.payroll.query.dto.NonTaxableDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NonTaxableMapper {

    // 비과세 항목 전체 조회
    List<NonTaxableDTO> findNonTaxablePayrolls(Integer elementsPerPage, Integer offset);

    // 총 비과세 항목 수
    Integer getTotalNonTaxablePayrolls();

}
