package com.pado.inflow.evaluation.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTypeDTO {

    private Long taskTypeId;
    private String taskTypeName;
}