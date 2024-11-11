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
public class VacationDTO {
    private Long vacationId;
    private String vacationName;
    private Long vacationLeft;
    private Long vacationUsed;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private String expirationStatus;
    private Long employeeId;
    private String vacationTypeName;
}
