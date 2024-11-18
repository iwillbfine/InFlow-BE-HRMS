package com.pado.inflow.employee.info.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "attendance_status_type")
@Data
public class AttendanceStatusType {
    @Id
    @Column(name = "attendance_status_type_code", nullable = false, length = 255)
    private String attendanceStatusTypeCode;

    @Column(name = "attendance_status_type_name", nullable = false, length = 255)
    private String attendanceStatusTypeName;
}
