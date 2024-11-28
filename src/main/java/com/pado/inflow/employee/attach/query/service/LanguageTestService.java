package com.pado.inflow.employee.attach.query.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.query.dto.LanguageTestDTO;
import com.pado.inflow.employee.attach.query.repository.LanguageTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service("LTQueryService")
public class LanguageTestService {

    private final LanguageTestMapper languageTestMapper;

    @Autowired
    public LanguageTestService(LanguageTestMapper languageTestMapper) {
        this.languageTestMapper = languageTestMapper;
    }

    // 사원의 어학 정보 조회
    public List<LanguageTestDTO> getLanguageTestsAll(String employeeId) {
        return Optional.ofNullable(languageTestMapper.getAllLanguageTests(employeeId))
                .filter(lang -> !lang.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LANGUAGE_TEST));
    }

    // 언어 코드 조회
    public List<HashMap<String, String>> getLanguageCode() {
        return Optional.ofNullable(languageTestMapper.getAllLanguageCodes())
                .filter(lang -> !lang.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LANGUAGE));
    }
}
