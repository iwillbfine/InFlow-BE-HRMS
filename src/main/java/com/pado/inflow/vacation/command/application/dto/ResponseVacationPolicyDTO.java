package com.pado.inflow.vacation.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseVacationPolicyDTO {
    private Long vacationPolicyId;
    private String vacationPolicyName;
    private String vacationPolicyDescription;
    private Long allocationDays;
    private String paidStatus;
    private Integer year;
    private LocalDateTime createdAt;
    private String autoAllocationCycle;
    private Long vacationTypeId;
    private Long policyRegisterId;
}
