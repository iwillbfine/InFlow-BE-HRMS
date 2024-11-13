package com.pado.inflow.vacation.command.application.dto;

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
public class RequestVacationRequestDTO {
    private String startDate;
    private String endDate;
    private String requestReason;
    private Long employeeId;
    private Long vacationId;
    private List<Map<String, String>> attachments;
}
