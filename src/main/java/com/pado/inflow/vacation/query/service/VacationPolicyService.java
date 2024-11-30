package com.pado.inflow.vacation.query.service;

import com.pado.inflow.vacation.query.dto.VacationPolicyDTO;

import java.util.List;

public interface VacationPolicyService {

    // 연도별 휴가 정책 조회
    List<VacationPolicyDTO> findVacationPoliciesByYear(Integer year);

    // 연도별 비정기 휴가 정책 조회
    List<VacationPolicyDTO> findIrregularVacationPoliciesByYear(Integer year);

}
