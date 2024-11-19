package com.pado.inflow.evaluation.query.service;

import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateTaskTypeResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEntity;
import com.pado.inflow.evaluation.command.domain.repository.TaskTypeRepository;
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
    private TaskTypeService queryTaskTypeService;    // @Autowired or 생성자 주입 둘 중 하나만 표시 ( 둘 다 사용하면 에러 )
    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Test
    @DisplayName("과제 유형 조회 테스트")
    void findAllTaskTypesTest() {
        // given
        TaskTypeDTO testData = new TaskTypeDTO(1L, "개인평가");

        // when
        List<TaskTypeDTO> actualList = queryTaskTypeService.findAllTaskTypes();

        // then
        assertEquals(testData.getTaskTypeName(), actualList.get(0).getTaskTypeName());
    }

    @Test
    @DisplayName("과제 유형 생성 테스트")
    void TaskTypeCreateTest() {
        //given
        CreateTaskTypeRequestDTO testData = new CreateTaskTypeRequestDTO();
        testData.setTaskTypeName("과제유형 삽입 테스트");

        //when
        TaskTypeEntity taskTypeEntity = testData.toEntity();
        TaskTypeEntity savedEntity = taskTypeRepository.save(taskTypeEntity);

        CreateTaskTypeResponseDTO selectedData =
                new CreateTaskTypeResponseDTO(savedEntity.getTaskTypeId(), savedEntity.getTaskTypeName());

        //then
        assertEquals(testData.getTaskTypeName(), selectedData.getTaskTypeName());

    }

}