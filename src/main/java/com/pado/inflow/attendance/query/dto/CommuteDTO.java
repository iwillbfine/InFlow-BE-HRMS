package com.pado.inflow.attendance.query.dto;

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
public class CommuteDTO {
    private Long commuteId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String remoteStatus;
    private String overtimeStatus;
    private Long employeeId;
    private Long attendanceRequestId;
}
