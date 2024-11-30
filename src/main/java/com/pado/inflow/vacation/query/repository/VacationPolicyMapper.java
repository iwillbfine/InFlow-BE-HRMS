package com.pado.inflow.vacation.query.repository;

import com.pado.inflow.vacation.query.dto.VacationPolicyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VacationPolicyMapper {

    // 연도별 휴가 정책 조회
    List<VacationPolicyDTO> findVacationPoliciesByYear(Integer year);

    // 연도별 비정기 휴가 정책 조회
    List<VacationPolicyDTO> findIrregularVacationPoliciesByYear(Integer year);

}
