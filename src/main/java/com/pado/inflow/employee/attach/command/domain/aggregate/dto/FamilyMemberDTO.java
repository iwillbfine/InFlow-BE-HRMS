package com.pado.inflow.employee.attach.command.domain.aggregate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilyMemberDTO {
    private Long familyMemberId;
    private String name;
    private LocalDate birthDate;
    private Long employeeId;
    private Long familyRelationshipCode;

    public FamilyMemberDTO(String name,
                           LocalDate birthDate,
                           Long employeeId,
                           Long familyRelationshipCode) {
        this.name = name;
        this.birthDate = birthDate;
        this.employeeId = employeeId;
        this.familyRelationshipCode = familyRelationshipCode;
    }
}
