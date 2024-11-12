package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.EducationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EducationMapper {

    // 전 사원의 학력 조회
    List<EducationDTO> getAllEducation();

    // 사원 한 명의 학력 조회
    List<EducationDTO> getOneEducation(Long employeeId);
}
