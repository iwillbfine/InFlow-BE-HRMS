package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.DisciplineRewardDTO;

import java.util.List;

public interface DisciplineRewardService {

    // 사원의 포상및징계 등록
    List<DisciplineRewardDTO> insertDisciplineRewards(List<DisciplineRewardDTO> drs);

    // 사원의 포상및징계 수정
    List<DisciplineRewardDTO> modifyDisciplineRewards(List<DisciplineRewardDTO> drs);

    // 사원의 포상및징계 삭제
    Boolean deleteDisciplineRewards(List<Long> drs);
}
