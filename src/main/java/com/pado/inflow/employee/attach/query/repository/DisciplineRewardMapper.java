package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.DisciplineRewardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DisciplineRewardMapper {

    // 사원의 포상 및 징계 정보 가져오기
    List<DisciplineRewardDTO> getDRList(String empId);
}
