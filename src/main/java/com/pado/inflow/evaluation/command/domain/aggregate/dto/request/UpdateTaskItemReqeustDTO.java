package com.pado.inflow.evaluation.command.domain.aggregate.dto.request;

import lombok.Data;

@Data
public class UpdateTaskItemReqeustDTO {

    private String taskName;
    private String taskContent;
    private Long evaluationPolicyId;
}
