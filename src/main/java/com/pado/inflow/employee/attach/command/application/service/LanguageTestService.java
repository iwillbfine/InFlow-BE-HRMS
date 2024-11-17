package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.LanguageTestDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.LanguageTest;

import java.util.List;

public interface LanguageTestService {

    // 사원의 어학 정보 등록
    List<LanguageTest> addLangTests(List<LanguageTestDTO> langTests);

    // 사원의 어학 정보 수정
    List<LanguageTest> modifyLangTests(List<LanguageTestDTO> langTests);
}
