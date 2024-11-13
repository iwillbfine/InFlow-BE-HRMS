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
public class ResponseVacationRequestDTO {
    private Long vacationRequestId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private String requestReason;
    private String requestStatus;
    private String rejectionReason;
    private LocalDateTime canceledAt;
    private String cancelReason;
    private String cancelStatus;
    private Long employeeId;
    private Long vacationId;
}
