package com.pado.inflow.evaluation.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.request.CreateTaskTypeRequestDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.dto.response.CreateTaskTypeResponseDTO;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEntity;
import com.pado.inflow.evaluation.command.domain.repository.TaskTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // Response용 DTO 생성

        CreateTaskTypeResponseDTO responseDTO = new CreateTaskTypeResponseDTO(savedEntity.getTaskTypeId(), savedEntity.getTaskTypeName());
        return responseDTO;
    }
}
