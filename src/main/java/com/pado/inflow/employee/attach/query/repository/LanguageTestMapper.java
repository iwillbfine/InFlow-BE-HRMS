package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.LanguageTestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LanguageTestMapper {

    // 사원의 어학 정보 조회
    List<LanguageTestDTO> getAllLanguageTests(String employeeId);
}
