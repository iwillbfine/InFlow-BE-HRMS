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
public class ResponseVacationDTO {
    private Integer vacationId;
    private String vacationName;
    private Long vacationLeft;
    private Long vacationUsed;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private String expirationStatus;
    private Long employeeId;
    private Long vacationPolicyId;
    private Long vacationTypeId;
}
