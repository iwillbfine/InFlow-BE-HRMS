package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.QualificationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QualificationMapper {

    // 사원의 경력 조회
    List<QualificationDTO> getAllQualifications(String employeeId);
}
