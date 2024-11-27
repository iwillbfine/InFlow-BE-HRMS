package com.pado.inflow.evaluation.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTypeDTO {

    @JsonProperty("task_type_id")
    private Long taskTypeId;

    @JsonProperty("task_type_name")
    private String taskTypeName;
}