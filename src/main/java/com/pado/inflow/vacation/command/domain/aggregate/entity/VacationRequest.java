package com.pado.inflow.vacation.command.domain.aggregate.entity;

import com.pado.inflow.vacation.command.domain.aggregate.type.CancelStatus;
import com.pado.inflow.vacation.command.domain.aggregate.type.RequestStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "vacation_request")
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_request_id")
    private Long vacationRequestId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "request_reason")
    private String requestReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @Column(name = "cancel_reason")
    private String cancelReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancel_status")
    private CancelStatus cancelStatus;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "vacation_id")
    private Long vacationId;

}
