package com.pado.inflow.vacation.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestVacationPolicyDTO {
    private String vacationPolicyName;
    private String vacationPolicyDescription;
    private Long allocationDays;
    private String paidStatus;
    private Integer year;
    private String autoAllocationCycle;
    private Long vacationTypeId;
    private Long policyRegisterId;
}
