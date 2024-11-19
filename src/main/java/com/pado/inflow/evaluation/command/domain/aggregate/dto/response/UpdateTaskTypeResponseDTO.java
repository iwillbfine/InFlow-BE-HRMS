package com.pado.inflow.evaluation.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateTaskTypeResponseDTO {

    @JsonProperty("task_type_id")
    private Long taskTypeId;

    @JsonProperty("task_type_name")
    private String taskTypeName;

    public UpdateTaskTypeResponseDTO() {
    }

    public UpdateTaskTypeResponseDTO(Long taskTypeId, String taskTypeName) {
        this.taskTypeId = taskTypeId;
        this.taskTypeName = taskTypeName;
    }
}
