package com.pado.inflow.chatbot.controller;

import com.pado.inflow.chatbot.dto.AIRequestDTO;
import com.pado.inflow.chatbot.dto.AIResponseDTO;
import com.pado.inflow.chatbot.dto.ChatbotSessionDTO;
import com.pado.inflow.chatbot.dto.SessionHistoryDTO;
import com.pado.inflow.chatbot.service.AIService;
import com.pado.inflow.common.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatbot")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    // 설명.1. AI 서버와 통신 및 세션 생성
    @PostMapping("/query")
    public ResponseDTO<AIResponseDTO> queryChatbot(@RequestBody AIRequestDTO chatbotRequest) {
        // 세션이 없으면 생성
        aiService.ensureSessionExists(chatbotRequest);

        // AI 서버와 통신
        AIResponseDTO response = aiService.communicateWithAI(chatbotRequest);

        return ResponseDTO.ok(response);
    }

    //설명.2. 사원별 세션 목록 조회
    @GetMapping("/sessions/{employeeId}")
    public ResponseDTO<List<ChatbotSessionDTO>> getChatbotSessions(@PathVariable("employeeId") Long employeeId) {
        List<ChatbotSessionDTO> sessions = aiService.getChatbotSessions(employeeId);
        return ResponseDTO.ok(sessions);
    }

    //설명.3. 특정 세션의 대화 이력 조회
    @GetMapping("/session/{sessionId}/history")
    public ResponseDTO<List<SessionHistoryDTO>> getSessionHistory(@PathVariable("sessionId") String sessionId) {
        List<SessionHistoryDTO> histories = aiService.getSessionHistory(sessionId);
        return ResponseDTO.ok(histories);
    }

}
