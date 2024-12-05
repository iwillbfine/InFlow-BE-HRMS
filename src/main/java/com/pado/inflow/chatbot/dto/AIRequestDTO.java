package com.pado.inflow.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AIRequestDTO {
    @JsonProperty("query")
    private String query;
    @JsonProperty("employee_id")
    private String employeeId;
    @JsonProperty("session_id")
    private String sessionId;
}
