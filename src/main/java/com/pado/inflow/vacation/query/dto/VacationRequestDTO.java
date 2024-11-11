package com.pado.inflow.vacation.query.dto;

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
public class VacationRequestDTO {
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
    private String name;
    private Long vacationId;
    private List<VacationRequestFileDTO> attachments;
}
