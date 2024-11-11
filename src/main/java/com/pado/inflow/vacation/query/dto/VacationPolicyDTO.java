package com.pado.inflow.vacation.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VacationPolicyDTO {
    private Long vacationPolicyId;
    private String vacationPolicyName;
    private Long allocationDays;
    private String paidStatus;
    private Integer year;
    private LocalDateTime createdAt;
    private String autoAllocationCycle;
    private String vacationTypeName;
}
