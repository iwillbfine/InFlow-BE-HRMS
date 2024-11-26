package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.AllAttachedInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AllAttachedInfoMapper {

    // 사원의 등록된 정보(자격증, 경력, ...) 정보 가져오기
    List<AllAttachedInfoDTO> getAIList(String empId);

    List<AllAttachedInfoDTO> getAIListDept(String deptCode);
}
