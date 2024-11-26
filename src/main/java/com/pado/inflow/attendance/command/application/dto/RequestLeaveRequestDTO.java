package com.pado.inflow.attendance.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private List<MultipartFile> attachments;
}
