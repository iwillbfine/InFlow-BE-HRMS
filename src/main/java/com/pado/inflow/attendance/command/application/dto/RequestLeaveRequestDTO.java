package com.pado.inflow.attendance.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestLeaveRequestDTO {
    private String requestReason;
    private String startDate;
    private String endDate;
    private Long employeeId;
    private Long attendanceRequestTypeId;
    private List<Map<String, String>> attachments;
}
