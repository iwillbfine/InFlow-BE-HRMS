package com.pado.inflow.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatbotSessionDTO {

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("employee_id")
    private Long employeeId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("first_question")
    private String firstQuestion;
}
