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
public class LeaveReturnDTO {
    private Long leaveReturnId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long employeeId;
    private Long attendanceRequestId;
}
