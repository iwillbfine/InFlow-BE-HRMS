package com.pado.inflow.employee.attach.command.domain.aggregate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilyMemberListDTO {
    private Long familyMemberId;
    private String name;
    private LocalDate birthDate;
    private Long employeeId;
    private String familyRelationshipName;
}
