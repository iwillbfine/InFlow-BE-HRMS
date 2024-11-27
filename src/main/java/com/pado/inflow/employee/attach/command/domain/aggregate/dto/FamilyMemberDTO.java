package com.pado.inflow.employee.attach.command.domain.aggregate.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FamilyMemberDTO {
    private Long familyMemberId;
    private String name;
    private LocalDate birthDate;
    private Long employeeId;
    private String familyRelationshipCode;

    public FamilyMemberDTO(String name,
                           LocalDate birthDate,
                           Long employeeId,
                           String familyRelationshipCode) {
        this.name = name;
        this.birthDate = birthDate;
        this.employeeId = employeeId;
        this.familyRelationshipCode = familyRelationshipCode;
    }
}
