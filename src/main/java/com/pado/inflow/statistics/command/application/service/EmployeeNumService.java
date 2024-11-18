package com.pado.inflow.statistics.command.application.service;

import com.pado.inflow.statistics.command.domain.aggregate.entity.EmployeeNum;

import java.util.List;

public interface EmployeeNumService {

    // 사원수 통계 초기화
    List<EmployeeNum> initEmployeeNum();

    // 매달 사원수 통계 업데이트
    void updateEmployeeNum();
}
