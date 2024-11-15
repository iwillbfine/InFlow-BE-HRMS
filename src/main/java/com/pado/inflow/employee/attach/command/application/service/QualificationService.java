package com.pado.inflow.employee.attach.command.application.service;


import com.pado.inflow.employee.attach.command.domain.aggregate.dto.QualificationDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Qualification;

import java.util.List;

public interface QualificationService {

    // 사원의 경력정보 등록
    List<Qualification> addQualifications(List<QualificationDTO> quals);
}
