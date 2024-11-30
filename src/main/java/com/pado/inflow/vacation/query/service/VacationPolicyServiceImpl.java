package com.pado.inflow.vacation.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.vacation.query.dto.VacationPolicyDTO;
import com.pado.inflow.vacation.query.repository.VacationPolicyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationPolicyServiceImpl implements VacationPolicyService {

    private final VacationPolicyMapper vacationPolicyMapper;

    public VacationPolicyServiceImpl(VacationPolicyMapper vacationPolicyMapper) {
        this.vacationPolicyMapper = vacationPolicyMapper;
    }

    // 연도별 휴가 정책 조회
    @Override
    public List<VacationPolicyDTO> findVacationPoliciesByYear(Integer year) {
        List<VacationPolicyDTO> vacationPolicies = vacationPolicyMapper.findVacationPoliciesByYear(year);
        if (vacationPolicies == null || vacationPolicies.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION_POLICY);
        }
        return vacationPolicies;
    }

    // 연도별 비정기 휴가 정책 조회
    @Override
    public List<VacationPolicyDTO> findIrregularVacationPoliciesByYear(Integer year) {
        List<VacationPolicyDTO> vacationPolicies = vacationPolicyMapper.findIrregularVacationPoliciesByYear(year);
        if (vacationPolicies == null || vacationPolicies.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_VACATION_POLICY);
        }
        return vacationPolicies;
    }

}
