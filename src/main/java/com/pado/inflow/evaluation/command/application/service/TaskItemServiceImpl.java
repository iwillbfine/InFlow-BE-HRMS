package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.department.query.dto.DepartmentMemberDTO;
import com.pado.inflow.department.query.repository.DepartmentMemberMapper;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskItemRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskItemReqeustDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.TaskItemResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskItemEntity;
import com.pado.inflow.evaluation.command.domain.repository.TaskItemRepository;
import com.pado.inflow.evaluation.query.dto.EvaluationPolicyDTO;
import com.pado.inflow.evaluation.query.dto.TaskItemDTO;
import com.pado.inflow.evaluation.query.dto.TaskTypeDTO;
import com.pado.inflow.evaluation.query.repository.EvaluationPolicyMapper;
import com.pado.inflow.evaluation.query.repository.TaskItemMapper;
import com.pado.inflow.evaluation.query.repository.TaskTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;

import static java.time.LocalDateTime.now;

@Service("CommandTaskItemService")
@Transactional
public class TaskItemServiceImpl implements TaskItemService {

    @Autowired
    private final TaskItemRepository taskItemRepository;
    private final DepartmentMemberMapper departmentMemberMapper;
    @Autowired
    private EvaluationPolicyMapper evaluationPolicyMapper;
    @Autowired
    private TaskTypeMapper taskTypeMapper;
    @Autowired
    private TaskItemMapper taskItemMapper;

    public TaskItemServiceImpl(TaskItemRepository taskItemRepository, DepartmentMemberMapper departmentMemberMapper) {
        this.taskItemRepository = taskItemRepository;
        this.departmentMemberMapper = departmentMemberMapper;
    }

    @Override
    public TaskItemResponseDTO createTaskItem(int year, String half, Long taskTypeId, CreateTaskItemRequestDTO createTaskItemRequestDTO) {
        // 사원 유효성 검사 및 부서코드 조회 ( 부서장 여부 )
        DepartmentMemberDTO selectedMember =
                departmentMemberMapper.findDepartmentMemberByEmployeeId(createTaskItemRequestDTO.getEmployeeId());

        // 정책 검사 ( 유효성 검사 )
        EvaluationPolicyDTO selectedPolicy =
                evaluationPolicyMapper.findPolicyByYearAndHalfAndTaskTypeId(year, half, taskTypeId);

        // 과제 유형 조회
        TaskTypeDTO selectedTaskType = taskTypeMapper.findTaskTypeByTaskTypeId(taskTypeId);
        TaskItemDTO taskItemData = new TaskItemDTO();

        // 4. 입력값 유효성 검사 ( trim() 사용 공백 처리 )
        if (createTaskItemRequestDTO.getTaskName() == null || createTaskItemRequestDTO.getTaskName().trim().isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK_NAME);
        }
        if (createTaskItemRequestDTO.getTaskContent() == null || createTaskItemRequestDTO.getTaskContent().trim().isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_TASK_ITEM_CONTENT);
        }

        taskItemData.setTaskName(createTaskItemRequestDTO.getTaskName());
        taskItemData.setTaskContent(createTaskItemRequestDTO.getTaskContent());
        taskItemData.setTaskTypeId(selectedTaskType.getTaskTypeId());
        taskItemData.setEmployeeId(createTaskItemRequestDTO.getEmployeeId());
        taskItemData.setDepartmentCode(selectedMember.getDepartmentCode());
        taskItemData.setEvaluationPolicyId(selectedPolicy.getEvaluationPolicyId());

        // 부서장 여부
        if ("Y".equals(selectedMember.getManagerStatus())) {
            taskItemData.setIsManagerWritten(true);
            taskItemData.setAssignedEmployeeCount(0L);
        } else {
            taskItemData.setIsManagerWritten(false);
            taskItemData.setAssignedEmployeeCount(1L);
        }

        // 생성
        TaskItemEntity savedTaskItem = taskItemRepository.save(taskItemData.toEntity());
        TaskItemResponseDTO responseDTO = savedTaskItem.toResponseDTO();

        return responseDTO;
    }

    @Override
    public TaskItemResponseDTO UpdateTaskItem(Long taskItemId, UpdateTaskItemReqeustDTO taskItemUpdateRequestDTO) {
        // 평가 정책 조회
        EvaluationPolicyDTO selectedPolicy =
                evaluationPolicyMapper.getEvaluationPolicyByEvaluationPolicyId(taskItemUpdateRequestDTO.getEvaluationPolicyId());

        LocalDateTime now = LocalDateTime.now();

        // 평가 시작일 이전 - 수정 가능
        if (now.isBefore(selectedPolicy.getStartDate())) {
            TaskItemDTO selectedData = taskItemMapper.findTaskItemByTaskItemId(taskItemId);
            selectedData.setTaskName(taskItemUpdateRequestDTO.getTaskName());
            selectedData.setTaskContent(taskItemUpdateRequestDTO.getTaskContent());

            TaskItemEntity savedTaskItem = taskItemRepository.save(selectedData.toEntity());
            return savedTaskItem.toResponseDTO();
        }

        // 평가 시작일 이후 - 수정 가능 월에만 수정 가능
        else {
            YearMonth currentYearMonth = YearMonth.now();
            YearMonth policyYearMonth = YearMonth.from(selectedPolicy.getModifiableDate());

            if (!currentYearMonth.equals(policyYearMonth)) {
                throw new CommonException(ErrorCode.NOT_IN_MODIFICATION_PERIOD);
            }

            TaskItemDTO selectedData = taskItemMapper.findTaskItemByTaskItemId(taskItemId);
            selectedData.setTaskName(taskItemUpdateRequestDTO.getTaskName());
            selectedData.setTaskContent(taskItemUpdateRequestDTO.getTaskContent());

            TaskItemEntity savedTaskItem = taskItemRepository.save(selectedData.toEntity());
            return savedTaskItem.toResponseDTO();
        }
    }
}
