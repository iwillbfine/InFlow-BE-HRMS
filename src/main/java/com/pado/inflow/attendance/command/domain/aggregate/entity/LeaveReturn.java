package com.pado.inflow.attendance.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "leave_return")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LeaveReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_return_id")
    private Long leaveReturnId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "attendance_request_id")
    private Long attendanceRequestId;

}
