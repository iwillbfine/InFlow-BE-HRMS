package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pado.inflow.evaluation.command.domain.aggregate.entity.TaskTypeEntity;
import lombok.Data;

@Data
public class DeleteTaskTypeRequestDTO {
    @JsonProperty("task_type_name")
    private String taskTypeName;

    // DTO to Entity
    public TaskTypeEntity toEntity() {
        return TaskTypeEntity.builder()
                .taskTypeName(this.taskTypeName)
                .build();
    }
}
