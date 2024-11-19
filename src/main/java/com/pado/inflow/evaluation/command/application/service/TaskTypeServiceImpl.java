package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.UpdateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateTaskTypeResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.UpdateTaskTypeResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.EvaluationPolicyEntity;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEntity;
import com.pado.inflow.evaluation.command.domain.repository.TaskTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service("commandTaskTypeService")
public class TaskTypeServiceImpl implements TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;

    public TaskTypeServiceImpl(TaskTypeRepository taskTypeRepository) {
        this.taskTypeRepository = taskTypeRepository;
    }

    // 과제 생성
    @Override
    @Transactional
    public CreateTaskTypeResponseDTO createTaskTypeByTaskTypeName(CreateTaskTypeRequestDTO createTaskTypeRequestDTO) {

        // 이미 존재하는 과제 유형인지 확인
        String taskTypeName = createTaskTypeRequestDTO.getTaskTypeName();
        if (taskTypeRepository.findByTaskTypeName(taskTypeName).isPresent()) {
            throw new CommonException(ErrorCode.DUPLICATE_TASK_TYPE);
        }

        // 이름이 중복되지 않는다면, 과제 유형 저장
        TaskTypeEntity taskTypeEntity = createTaskTypeRequestDTO.toEntity();
        TaskTypeEntity savedEntity = taskTypeRepository.save(taskTypeEntity);

        // ResponseDTO 생성
        CreateTaskTypeResponseDTO responseDTO =
                new CreateTaskTypeResponseDTO(savedEntity.getTaskTypeId(), savedEntity.getTaskTypeName());
        return responseDTO;
    }

    @Override
    @Transactional
    public UpdateTaskTypeResponseDTO updateTaskTypeByTaskTypeId(Long taskTypeId, UpdateTaskTypeRequestDTO updateTaskTypeRequestDTO) {

        // 해당 과제 유형 존재하는지 확인
        TaskTypeEntity taskType = taskTypeRepository.findByTaskTypeId(taskTypeId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_TASK_TYPE));

        // 과제 유형과 연결된 정책 확인
        List<EvaluationPolicyEntity> evaluationPolicies =
                taskTypeRepository.findEvaluationsPoliciesByTaskTypeId(taskTypeId);

        if ( evaluationPolicies != null ) {
            throw new CommonException(ErrorCode.UPDATE_DENIED);
        }

        // 모두 통과하면 수정
        taskType.updateTaskTypeName(updateTaskTypeRequestDTO.getTaskTypeName());
        TaskTypeEntity updatedEntity = taskTypeRepository.save(taskType);

        UpdateTaskTypeResponseDTO responseDTO = new UpdateTaskTypeResponseDTO(updatedEntity.getTaskTypeId(), updatedEntity.getTaskTypeName());
        return responseDTO;
    }
}
