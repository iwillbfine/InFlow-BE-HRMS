package com.pado.inflow.attendance.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommuteDTO {

    @JsonProperty("commute_id")
    private Long commuteId;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("remote_status")
    private String remoteStatus;

    @JsonProperty("overtime_status")
    private String overtimeStatus;

    @JsonProperty("employee_id")
    private Long employeeId;

    @JsonProperty("attendance_request_id")
    private Long attendanceRequestId;
}
