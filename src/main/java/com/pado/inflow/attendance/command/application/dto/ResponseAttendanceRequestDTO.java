package com.pado.inflow.attendance.command.application.dto;

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
public class ResponseAttendanceRequestDTO {
    private Long attendanceRequestId;
    private String requestReason;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private String rejectionReason;
    private String requestStatus;
    private LocalDateTime canceledAt;
    private String cancelReason;
    private String cancelStatus;
    private String destination;
    private Long employeeId;
    private Long attendanceRequestTypeId;
}
