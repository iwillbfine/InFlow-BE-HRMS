package com.pado.inflow.attendance.command.domain.aggregate.entity;

import com.pado.inflow.attendance.command.domain.aggregate.type.TripType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "business_trip")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_trip_id")
    private Long businessTripId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type")
    private TripType tripType;

    @Column(name = "destination")
    private String destination;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "attendance_request_id")
    private Long attendanceRequestId;

}
