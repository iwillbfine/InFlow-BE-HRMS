package com.pado.inflow.evaluation.command;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.application.service.TaskItemService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskItemRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskItemResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TaskItemServiceImplTests {

    @Autowired
    private TaskItemService taskItemService;

    @Test
    @DisplayName("과제 생성 성공 테스트")
    void createTaskItemSuccessTest() {
        // given
        CreateTaskItemRequestDTO requestDTO = new CreateTaskItemRequestDTO();
        requestDTO.setTaskName("테스트 과제");
        requestDTO.setTaskContent("테스트 과제 내용입니다.");
        requestDTO.setEmployeeId(1L);  // DB에 존재하는 employeeId

        int year = 2023;
        String half = "1st";
        Long taskTypeId = 1L;  // DB에 존재하는 taskTypeId

        // when
        TaskItemResponseDTO responseDTO = taskItemService.createTaskItem(year, half, taskTypeId, requestDTO);

        // then
        assertNotNull(responseDTO);
        assertEquals(requestDTO.getTaskName(), responseDTO.getTaskName());
        assertEquals(requestDTO.getTaskContent(), responseDTO.getTaskContent());
        assertEquals(requestDTO.getEmployeeId(), responseDTO.getEmployeeId());
    }

    @Test
    @DisplayName("과제 생성 실패 - 과제명 누락")
    void createTaskItemFailNoTaskNameTest() {
        // given
        CreateTaskItemRequestDTO requestDTO = new CreateTaskItemRequestDTO();
        requestDTO.setTaskName(""); // 빈 과제명
        requestDTO.setTaskContent("테스트 과제 내용입니다.");
        requestDTO.setEmployeeId(1L);

        int year = 2023;
        String half = "1st";
        Long taskTypeId = 1L;

        // when & then
        CommonException exception = assertThrows(CommonException.class, () -> {
            taskItemService.createTaskItem(year, half, taskTypeId, requestDTO);
        });

        assertEquals(ErrorCode.NOT_FOUND_TASK_NAME.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("과제 생성 실패 - 과제 내용 누락")
    void createTaskItemFailNoContentTest() {
        // given
        CreateTaskItemRequestDTO requestDTO = new CreateTaskItemRequestDTO();
        requestDTO.setTaskName("테스트 과제");
        requestDTO.setTaskContent("   "); // 공백만 있는 내용
        requestDTO.setEmployeeId(1L);

        int year = 2023;
        String half = "1st";
        Long taskTypeId = 1L;

        // when & then
        CommonException exception = assertThrows(CommonException.class, () -> {
            taskItemService.createTaskItem(year, half, taskTypeId, requestDTO);
        });

        assertEquals(ErrorCode.NOT_FOUND_TASK_ITEM_CONTENT.getMessage(), exception.getMessage());
    }

}
