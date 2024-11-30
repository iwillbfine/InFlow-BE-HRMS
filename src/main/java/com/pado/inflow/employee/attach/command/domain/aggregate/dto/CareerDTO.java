package com.pado.inflow.employee.attach.command.domain.aggregate.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CareerDTO {
    private Long careerId;
    private String companyName;
    private String roleName;
    private LocalDate joinDate;
    private LocalDate resignationDate;
    private Long employeeId;

    public CareerDTO(String companyName, String roleName, LocalDate joinDate, LocalDate resignationDate, Long employeeId) {
        this.companyName = companyName;
        this.roleName = roleName;
        this.joinDate = joinDate;
        this.resignationDate = resignationDate;
        this.employeeId = employeeId;
    }
}
