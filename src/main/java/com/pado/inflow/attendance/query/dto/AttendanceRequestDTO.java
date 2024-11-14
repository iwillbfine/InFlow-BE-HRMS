package com.pado.inflow.attendance.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceRequestDTO {
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
    private String name;
    private Long attendanceRequestTypeId;
    private String attendanceRequestTypeName;
    private List<AttendanceRequestFileDTO> attachments;
}
