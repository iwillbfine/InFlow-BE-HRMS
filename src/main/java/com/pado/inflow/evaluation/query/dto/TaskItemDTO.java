package com.pado.inflow.evaluation.query.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskItemDTO {

    private Long taskItemId;
    private String taskName;
    private String taskContent;
    private Long assignedEmployeeCount;
    private Long taskTypeId;
    private Long employeeId;
    private String departmentCode;
    private Long evaluationPolicyId;
}