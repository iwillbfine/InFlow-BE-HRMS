package com.pado.inflow.vacation.command.application.service;

import com.pado.inflow.vacation.command.application.dto.RequestVacationPolicyDTO;
import com.pado.inflow.vacation.command.application.dto.ResponseVacationPolicyDTO;

public interface VacationPolicyService {

    // 휴가 정책 등록
    ResponseVacationPolicyDTO registVacationPolicy(RequestVacationPolicyDTO reqVacationPolicyDTO);

}
