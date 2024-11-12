package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.employee.attach.query.dto.EducationDTO;
import com.pado.inflow.employee.attach.query.repository.EducationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EQueryService")
public class EducationService {

    private final EducationMapper educationMapper;

    @Autowired
    public EducationService(EducationMapper educationMapper) {
        this.educationMapper = educationMapper;
    }

    // 전 사원의 학력 조회
    public List<EducationDTO> getEduAll() {
        return educationMapper.getAllEducation();
    }

    // 사원 한 명의 학력 조회
    public List<EducationDTO> getEduOne(Long employeeId) {
        return educationMapper.getOneEducation(employeeId);
    }
}
