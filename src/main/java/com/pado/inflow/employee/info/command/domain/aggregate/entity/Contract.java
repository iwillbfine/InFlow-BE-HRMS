package com.pado.inflow.employee.info.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "contract")
@Data
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long contractId;

    @Column(name = "contract_type", nullable = false)
    private String contractType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_url", nullable = false, unique = true)
    private String fileUrl;

    @Column(name = "contract_status", nullable = false)
    private String contractStatus = "SIGNING";

    @Column(name = "consent_status", nullable = false)
    private String consentStatus = "Y";

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "reviewer_id", nullable = false)
    private Long reviewerId;

}
