package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.query.dto.QualificationDTO;
import com.pado.inflow.employee.attach.query.repository.QualificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("QQueryService")
public class QualificationService {

    private final QualificationMapper qualificationMapper;

    @Autowired
    public QualificationService(QualificationMapper qualificationMapper) {
        this.qualificationMapper = qualificationMapper;
    }

    // 전 사원의 자격증 조회
    public List<QualificationDTO> getQualificationAll() {
        return Optional.ofNullable(qualificationMapper.getAllQualifications())
                .filter(qual -> !qual.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_QUALIFICATION));
    }

    // 사원 한 명의 자격증 조회
    public List<QualificationDTO> getQualificationOne(Long employeeId) {
        return Optional.ofNullable(qualificationMapper.getOneQualification(employeeId))
                .filter(qul -> !qul.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_QUALIFICATION));
    }
}
