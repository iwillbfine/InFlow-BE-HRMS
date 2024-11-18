package com.pado.inflow.employee.info.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id", nullable = false, updatable = false)
    private Long appointmentId; // 인사발령 ID (PK)

    @Column(name = "appointed_at", nullable = false)
    private LocalDateTime appointedAt; // 발령 날짜

    @Column(name = "employee_id", nullable = false)
    private Long employeeId; // 발령 대상 사원 ID

    @Column(name = "authorizer_id", nullable = false)
    private Long authorizerId; // 발령 권한자 ID

    @Column(name = "department_code", nullable = false, length = 255)
    private String departmentCode; // 부서 코드

    @Column(name = "duty_code", nullable = false, length = 255)
    private String dutyCode; // 직무 코드

    @Column(name = "role_code", nullable = false, length = 255)
    private String roleCode; // 직책 코드

    @Column(name = "position_code", nullable = false, length = 255)
    private String positionCode; // 직위 코드

    @Column(name = "appointment_item_code", nullable = false)
    private String appointmentItemCode; // 인사발령 항목
}
