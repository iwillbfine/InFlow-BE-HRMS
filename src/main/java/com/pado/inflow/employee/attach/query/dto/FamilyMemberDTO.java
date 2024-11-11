package com.pado.inflow.employee.attach.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilyMemberDTO {
    private Long familyMemberId;
    private String name;
    private LocalDateTime birthDate;
    private Long employeeId;
    private String familyRelationshipName;
}
