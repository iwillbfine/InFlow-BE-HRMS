package com.pado.inflow.employee.info.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "appointment_item")
@Data
public class AppointmentItem {

    @Id
    @Column(name = "appointment_item_code", nullable = false, length = 255)
    private String appointmentItemCode; // 인사발령 항목 코드 (PK)

    @Column(name = "appointment_item_name", nullable = false, length = 255)
    private String appointmentItemName; // 인사발령 항목 이름
}

