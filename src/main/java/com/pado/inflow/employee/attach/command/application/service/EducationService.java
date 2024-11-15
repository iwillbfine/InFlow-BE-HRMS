package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.EducationDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Education;

import java.util.List;

public interface EducationService {

    // 사원의 학력정보 등록
    List<Education> addEdus(List<EducationDTO> educations);

    // 사원의 학력정보 수정
    List<Education> modifyEdus(List<EducationDTO> educations);

    // 사원의 학력정보 삭제
    Boolean deleteEdus(List<Long> educations);
}
