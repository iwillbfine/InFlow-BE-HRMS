package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.TaskItemDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskItemServiceImplTests {

    @Autowired
    private TaskItemService taskItemService;

    @Test
    @DisplayName("부서 과제 조회 테스트")
    void findDepartmentTasksTest() {
        //given
        TaskItemDTO testData = new TaskItemDTO();
        testData.setDepartmentCode("DP006");

        //when
        List<TaskItemDTO> selectedTasks = taskItemService.findTaskItemByEmpIdAndYearAndHalf(2023, "1st", 8L);

        //then
        assertNotNull(selectedTasks);
        assertEquals(testData.getDepartmentCode(), selectedTasks.get(0).getDepartmentCode());
    }

    @Test
    @DisplayName("개인 과제 조회 테스트")
    void findIndividualTasksTest() {
        //given
        TaskItemDTO testData = new TaskItemDTO();
        testData.setEmployeeId(7L);

        //when
        List<TaskItemDTO> selectedTasks = taskItemService.findindividualTaskItemByEmpId(2023, "1st", 7L);

        //then
        assertNotNull(selectedTasks);
        assertEquals(testData.getEmployeeId(), selectedTasks.get(0).getEmployeeId());
    }

    @Test
    @DisplayName("공통 과제 조회 테스트")
    void getCommonTasksTest() {
        //given
        TaskItemDTO testData = new TaskItemDTO();
        testData.setDepartmentCode("DP002");

        //when
        List<TaskItemDTO> selectedDatas = taskItemService.getCommonTaskItem(2023,"1st",6L);

        //then
        assertNotNull(selectedDatas);
        assertEquals(testData.getDepartmentCode(), selectedDatas.get(0).getDepartmentCode());
    }

}