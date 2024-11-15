package com.pado.inflow.attendance.command.domain.aggregate.entity;

import com.pado.inflow.attendance.command.domain.aggregate.type.CancelStatus;
import com.pado.inflow.attendance.command.domain.aggregate.type.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "attendance_request")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_request_id")
    private Long attendanceRequestId;

    @Column(name = "request_reason")
    private String requestReason;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @Column(name = "cancel_reason")
    private String cancelReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancel_status")
    private CancelStatus cancelStatus;

    @Column(name = "destination")
    private String destination;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "attendance_request_type_id")
    private Long attendanceRequestTypeId;

}
