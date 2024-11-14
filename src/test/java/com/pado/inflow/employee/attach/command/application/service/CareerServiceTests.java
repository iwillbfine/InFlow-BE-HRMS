package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.employee.attach.command.domain.aggregate.dto.CareerDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CareerServiceTests {

    private final CareerService careerService;

    @Autowired
    public CareerServiceTests(CareerService careerService) {
        this.careerService = careerService;
    }

    @DisplayName("사원의 학력정보 등록")
    @Test
    public void addEducation() {
        List<CareerDTO> list = new ArrayList<>();
        list.add(new CareerDTO("네이버주식회사",
                "데이터 엔지니어",
                LocalDate.of(2000, 3, 2),
                LocalDate.of(2006, 2, 15),
                1L));
        assertTrue(careerService.addCareers(list) != null);
    }

    @DisplayName("사원의 학력정보 수정")
    @Test
    public void modifyEducation() {
        List<CareerDTO> list = new ArrayList<>();
        list.add(new CareerDTO(1L,
                "삼성전자",
                "하드웨어 엔지니어",
                LocalDate.of(2010, 5, 1),
                LocalDate.of(2014, 8, 31),
                1L));
        list.add(new CareerDTO(2L,
                "LG전자",
                "마케팅 매니저",
                LocalDate.of(2000, 3, 2),
                LocalDate.of(2006, 2, 15),
                2L));
        assertTrue(careerService.modifyCareers(list) != null);
    }

    @DisplayName("사원의 학력정보 삭제")
    @Test
    public void deleteEducation() {
        List<Long> list = new ArrayList<>(List.of(1L, 2L));
        assertTrue(careerService.deleteCareers(list));
    }
}
