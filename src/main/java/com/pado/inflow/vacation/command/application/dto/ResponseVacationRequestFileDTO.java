package com.pado.inflow.vacation.command.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseVacationRequestFileDTO {
    private Long vacationRequestFileId;
    private String fileName;
    private String fileUrl;
    private Long vacationRequestId;
}
