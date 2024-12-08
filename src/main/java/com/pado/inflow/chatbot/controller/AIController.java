package com.pado.inflow.chatbot.controller;

import com.pado.inflow.chatbot.dto.AIRequestDTO;
import com.pado.inflow.chatbot.dto.AIResponseDTO;
import com.pado.inflow.chatbot.service.AIService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class AIController {

    private final AIService aiService;

    // Constructor Injection
    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/query")
    public ResponseDTO<AIResponseDTO> queryChatbot(@RequestBody AIRequestDTO chatbotRequest) {
        // AIService 호출 및 응답 처리
        AIResponseDTO response = aiService.communicateWithAI(chatbotRequest);
        return ResponseDTO.ok(response);
    }
}
