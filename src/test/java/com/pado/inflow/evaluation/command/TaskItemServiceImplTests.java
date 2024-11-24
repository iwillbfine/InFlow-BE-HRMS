package com.pado.inflow.evaluation.command;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.application.service.TaskItemService;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskItemRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskItemReqeustDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskItemResponseDTO;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
@Transactional
public class TaskItemServiceImplTests {

    @Autowired
    private TaskItemService taskItemService;
    @Autowired
    private EvaluationPolicyMapper evaluationPolicyMapper;

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
        TaskItemResponseDTO responseDTO =
                taskItemService.createTaskItem(year, half, taskTypeId, requestDTO);

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

    @Test
    @DisplayName("과제 수정 실패 테스트 - 수정 가능 기간이 아님")
    void updateTaskItemFailNotInPeriodTest() {
        // given
        Long taskItemId = 1L;  // DB에 존재하는 과제 ID

        UpdateTaskItemReqeustDTO updateRequest = new UpdateTaskItemReqeustDTO();
        updateRequest.setTaskName("수정된 과제명");
        updateRequest.setTaskContent("수정된 과제 내용");
        updateRequest.setEvaluationPolicyId(1L); // DB에 존재하는 정책 ID

        // 실제 평가 정책의 수정 가능 날짜와 현재 날짜가 다른지 검증
        EvaluationPolicyDTO policy = evaluationPolicyMapper.getEvaluationPolicyByEvaluationPolicyId(1L);
        YearMonth policyYearMonth = YearMonth.from(policy.getModifiableDate());
        YearMonth currentYearMonth = YearMonth.now();

        // 수정 가능 기간이 아닌 경우에만 테스트 실행
        assumeTrue(!currentYearMonth.equals(policyYearMonth), "현재가 수정 가능 기간이라 테스트를 진행할 수 없습니다.");

        // when & then
        assertThrows(CommonException.class, () -> {
            taskItemService.UpdateTaskItem(taskItemId, updateRequest);
        });
    }


    @Test
    @DisplayName("과제 수정 성공 테스트 - 별도의 더미데이터 삽입 후 진행할 것")
    void taskItemUpdateSuccessTest() {

    }
}
