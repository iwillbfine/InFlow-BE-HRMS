package com.pado.inflow.attendance.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "attendance_request_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceRequestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_request_type_id")
    private Long attendanceRequestTypeId;

    @Column(name = "attendance_request_type_name")
    private String attendanceRequestTypeName;

    @Column(name = "attendance_request_type_description")
    private String attendanceRequestTypeDescription;

}
