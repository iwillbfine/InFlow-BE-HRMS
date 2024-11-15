package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.query.dto.EducationDTO;
import com.pado.inflow.employee.attach.query.repository.EducationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("EQueryService")
public class EducationService {

    private final EducationMapper educationMapper;

    @Autowired
    public EducationService(EducationMapper educationMapper) {
        this.educationMapper = educationMapper;
    }

    // 전 사원의 학력 조회
    public List<EducationDTO> getEduAll() {
        return Optional.of(educationMapper.getAllEducation())
                .filter(edu -> !edu.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EDUCATION));
    }

    // 사원 한 명의 학력 조회
    public List<EducationDTO> getEduOne(Long employeeId) {
        return Optional.of(educationMapper.getOneEducation(employeeId))
                .filter(edu -> !edu.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EDUCATION));
    }
}
