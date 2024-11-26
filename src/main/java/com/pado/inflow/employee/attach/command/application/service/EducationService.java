package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.EducationDTO;

import java.util.List;

public interface EducationService {

    // 사원의 학력정보 등록
    List<EducationDTO> addEdus(List<EducationDTO> educations);

    // 사원의 학력정보 수정
    List<EducationDTO> modifyEdus(List<EducationDTO> educations);

    // 사원의 학력정보 삭제
    Boolean deleteEdus(List<Long> educations);
}
