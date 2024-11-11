package com.pado.inflow.vacation.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "vacation_request_file")
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_request_file_id")
    private Long vacationRequestFileId;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "vacation_request_id")
    private Long vacationRequestId;

}
