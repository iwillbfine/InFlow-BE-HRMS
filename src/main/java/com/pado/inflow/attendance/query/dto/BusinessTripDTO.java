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
public class BusinessTripDTO {
    private Long businessTripId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String tripType;
    private String destination;
    private Long employeeId;
    private Long attendanceRequestId;
}
