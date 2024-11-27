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
public class DisciplineRewardDTO {
    private Long disciplineRewardId;
    private String disciplineRewardName;
    private String content;
    private LocalDate createdAt;
    private Long employeeId;

    public DisciplineRewardDTO(String disciplineRewardName, String content, LocalDate createdAt, Long employeeId) {
        this.disciplineRewardName = disciplineRewardName;
        this.content = content;
        this.createdAt = createdAt;
        this.employeeId = employeeId;
    }
}
