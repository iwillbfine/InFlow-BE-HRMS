package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.LanguageTestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LanguageTestServiceTests {

    private final LanguageTestService languageTestService;

    @Autowired
    public LanguageTestServiceTests(LanguageTestService languageTestService) {
        this.languageTestService = languageTestService;
    }

    @DisplayName("사원의 어학 정보 등록")
    @Test
    public void addLangTests() {
        List<LanguageTestDTO> list = new ArrayList<>();
        list.add(new LanguageTestDTO("TOEIC",
                "123123123",
                "ETS",
                LocalDate.of(2023, 11, 1),
                "990",
                1L,
                "EN"));
        assertTrue(languageTestService.addLangTests(list).size() > 0);
    }
}
