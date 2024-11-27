package com.pado.inflow.employee.attach.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CareerDTO {
    private Long careerId;
    private String companyName;
    private String roleName;
    private LocalDate joinDate;
    private LocalDate resignationDate;
    private Long employeeId;
}
