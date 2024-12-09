package com.pado.inflow.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SessionHistoryDTO {

    @JsonProperty("session_history_id")
    private String sessionHistoryId;

    @JsonProperty("chatbot_type")
    private String chatbotType;

    @JsonProperty("chatbot_content")
    private String chatbotContent;
}
