package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.query.dto.DisciplineRewardDTO;
import com.pado.inflow.employee.attach.query.repository.DisciplineRewardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("DRService")
public class DisciplineRewardService {

    private final DisciplineRewardMapper disciplineRewardMapper;

    @Autowired
    public DisciplineRewardService(DisciplineRewardMapper disciplineRewardMapper) {
        this.disciplineRewardMapper = disciplineRewardMapper;
    }

    // 사원의 포상 및 징계 정보 가져오기
    public List<DisciplineRewardDTO> getDisciplineRewardsAll(String empId) {
        return Optional.of(disciplineRewardMapper.getDRList(empId))
                .filter(xx -> !xx.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DISCIPLINE_REWARD));
    }
}
