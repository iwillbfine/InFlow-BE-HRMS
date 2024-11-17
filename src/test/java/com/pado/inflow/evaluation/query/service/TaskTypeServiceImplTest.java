package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.query.dto.TaskTypeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskTypeServiceImplTest {

    @Autowired
    private TaskTypeService taskTypeService;    // @Autowired or 생성자 주입 둘 중 하나만 표시 ( 둘 다 사용하면 에러 )


    @Test
    @DisplayName("과제 유형 조회 테스트")
    void findAllTaskTypesTest() {
        // given
        TaskTypeDTO testData = new TaskTypeDTO(1L, "개인평가");

        // when
        List<TaskTypeDTO> actualList = taskTypeService.findAllTaskTypes();

        // then
        assertEquals(testData.getTaskTypeName(), actualList.get(0).getTaskTypeName());
    }


}