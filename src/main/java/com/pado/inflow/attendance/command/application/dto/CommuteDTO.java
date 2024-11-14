package com.pado.inflow.attendance.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.pado.inflow.attendance.command.domain.aggregate.type.OvertimeStatus;
import com.pado.inflow.attendance.command.domain.aggregate.type.RemoteStatus;
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
public class CommuteDTO {
    private Long commuteId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private RemoteStatus remoteStatus;
    private OvertimeStatus overtimeStatus;
    private Long employeeId;
    private Long attendanceRequestId;
}
