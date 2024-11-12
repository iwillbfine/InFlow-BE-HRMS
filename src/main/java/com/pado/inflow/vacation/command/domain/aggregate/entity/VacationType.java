package com.pado.inflow.vacation.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vacation_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VacationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_type_id")
    private Long vacationTypeId;

    @Column(name = "vacation_type_name")
    private String vacationTypeName;

}
