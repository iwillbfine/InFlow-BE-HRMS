package com.pado.inflow.employee.attach.query.repository;

import com.pado.inflow.employee.attach.query.dto.LanguageTestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LanguageTestMapper {

    // 전 사원의 자격증 정보 조회
    List<LanguageTestDTO> getAllLanguageTests();

    // 사원 한 명의 자격증 정보 조회
    List<LanguageTestDTO> getOneLanguageTest(Long employeeId);
}
