package com.pado.inflow.employee.info.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeInfoDTO {

    @JsonProperty("event_name")
    private String eventName;

    @JsonProperty("event_date")
    private LocalDateTime eventDate;

}
