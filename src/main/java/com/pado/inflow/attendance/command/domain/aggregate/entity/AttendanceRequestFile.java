package com.pado.inflow.attendance.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "attendance_request_file")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceRequestFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_request_file_id")
    private Long attendanceRequestFileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "attendance_request_id")
    private Long attendanceRequestId;

}
