package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.GradeDTO;
import com.pado.inflow.evaluation.query.repository.GradeMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GradeServiceImplTest {

    @Autowired
    private GradeMapper gradeMapper;

    @Test
    @DisplayName("등급 조회 테스트")
    void findGradeByYearAndHalfTest() {
        //given
        GradeDTO testData = new GradeDTO();
        testData.setGradeId(27L);
        testData.setGradeName("D");

        //when
        List<GradeDTO> gradeList = gradeMapper.findGradeByYearAndHalf(2023, "1st");

        //then
        assertNotNull(gradeList);
        assertEquals(testData.getGradeId(), gradeList.get(0).getGradeId());
        assertEquals(testData.getGradeName(), gradeList.get(0).getGradeName());
    }

    @Test
    @DisplayName("평가 등급 단건 조회 테스트")
    void gradeFindByGradeIdTest() {
        //given
        GradeDTO testData = new GradeDTO();
        testData.setGradeId(1L);

        //when
        GradeDTO selectedData = gradeMapper.getGradeByGradeId(1L);

        //then
        assertEquals(testData.getGradeId(), selectedData.getGradeId());
        
    }

}