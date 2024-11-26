package com.pado.inflow.employee.attach.query.dto;

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
public class DisciplineRewardDTO {
    private String disciplineRewardName;
    private String content;
    private LocalDate createdAt;
}
