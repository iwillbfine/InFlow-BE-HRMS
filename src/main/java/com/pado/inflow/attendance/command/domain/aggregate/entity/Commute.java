package com.pado.inflow.attendance.command.domain.aggregate.entity;

import com.pado.inflow.attendance.command.domain.aggregate.type.OvertimeStatus;
import com.pado.inflow.attendance.command.domain.aggregate.type.RemoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "commute")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Commute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commute_id")
    private Long commuteId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "remote_status")
    private RemoteStatus remoteStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "overtime_status")
    private OvertimeStatus overtimeStatus;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "attendance_request_id")
    private Long attendanceRequestId;

}
