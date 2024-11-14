package com.pado.inflow.attendance.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceRequestTypeDTO {
    private Long attendanceRequestTypeId;
    private String attendanceRequestTypeName;
    private String attendanceRequestTypeDescription;
}
