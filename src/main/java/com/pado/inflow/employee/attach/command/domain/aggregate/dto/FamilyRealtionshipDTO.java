package com.pado.inflow.employee.attach.command.domain.aggregate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilyRealtionshipDTO {
    private Long familyRealtionshipCode;
    private String familyRealtionshipName;

    public FamilyRealtionshipDTO(String familyRealtionshipName) {
        this.familyRealtionshipName = familyRealtionshipName;
    }
}
