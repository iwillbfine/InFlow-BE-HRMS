package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.TaskEvalDTO;
import com.pado.inflow.evaluation.query.repository.TaskEvalMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskEvalServiceImplTest {

    @Autowired
    private TaskEvalService taskEvalService;

    @Test
    @DisplayName("과제별 평가 내역 조회 테스트")
    void taskEvalListFindTest() {
        //given
        TaskEvalDTO testData = new TaskEvalDTO();
        testData.setTaskTypeId(2L);
        testData.setPerformanceInput("사내 캡스톤 대회에서 입상");

        //when
        List<TaskEvalDTO> selectedTaskEvalList = taskEvalService.findTaskEval(7L, 2023, "1st");

        //then
        assertNotNull(selectedTaskEvalList);
        assertEquals(testData.getTaskTypeId(), selectedTaskEvalList.get(16).getTaskTypeId());
        assertEquals(testData.getPerformanceInput(), selectedTaskEvalList.get(16).getPerformanceInput());

    }

}