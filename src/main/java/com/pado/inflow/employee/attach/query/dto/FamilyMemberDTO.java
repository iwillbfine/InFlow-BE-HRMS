package com.pado.inflow.employee.attach.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FamilyMemberDTO {
    private Long familyMemberId;
    private String name;
    private LocalDateTime birthDate;
    private Long employeeId;
    private String familyRelationshipName;
}
