package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.QualificationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QualificationMapper {

    // 전 사원의 경력 조회
    List<QualificationDTO> getAllQualifications();

    // 사원 한 명의 경력 조회
    List<QualificationDTO> getOneQualification(Long employeeId);
}
