package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.QualificationDTO;

import java.util.List;

public interface QualificationService {

    // 사원의 자격증 정보 등록
    List<QualificationDTO> addQualifications(List<QualificationDTO> quals);

    // 사원의 자격증 정보 수정
    List<QualificationDTO> modifyQualifications(List<QualificationDTO> qualifications);

    // 사원의 자격증 정보 삭제
    Boolean deleteQualifications(List<Long> qualifications);
}
