package com.pado.inflow.evaluation.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTaskTypeResponseDTO {

    @JsonProperty("task_type_id")
    private Long taskTypeId;

    @JsonProperty("task_type_name")
    private String taskTypeName;


    public CreateTaskTypeResponseDTO(Long taskTypeId, String taskTypeName) {
        this.taskTypeId = taskTypeId;
        this.taskTypeName = taskTypeName;
    }
}