package com.pado.inflow.attendance.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.pado.inflow.attendance.command.domain.aggregate.type.TripType;
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
public class BusinessTripDTO {
    private Long businessTripId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private TripType tripType;
    private String destination;
    private Long employeeId;
    private Long attendanceRequestId;
}
