package com.pado.inflow.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AIResponseDTO {
    @JsonProperty("answer")
    private String answer;

    @JsonProperty("selected_keyword")
    private String selectedKeyword;
}
