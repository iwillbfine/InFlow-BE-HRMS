package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskItemRequestDTO {
    private String taskName;
    private String taskContent;
//    private Long assignedEmployeeCount;
//    private Boolean isManagerWritten;
//    private Long taskTypeId;
    private Long employeeId;
//    private String departmentCode;
//    private Long evaluationPolicyId;

}

