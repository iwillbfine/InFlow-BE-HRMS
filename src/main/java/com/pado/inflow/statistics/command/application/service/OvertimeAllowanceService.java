package com.pado.inflow.statistics.command.application.service;

import com.pado.inflow.statistics.command.domain.aggregate.dto.OvertimeAllowanceDTO;

import java.util.List;

public interface OvertimeAllowanceService {

    // 초과근무수당 통계 생성
    List<OvertimeAllowanceDTO> initOvertimeAllowance();

    // 매달 초과근무수당 통계 업데이트
    void updateOvertimeAllowance();
}
