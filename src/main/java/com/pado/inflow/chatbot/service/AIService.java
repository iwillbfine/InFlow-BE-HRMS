package com.pado.inflow.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.pado.inflow.chatbot.dto.AIRequestDTO;
import com.pado.inflow.chatbot.dto.AIResponseDTO;


@Service
public class AIService {
    private final RestTemplate restTemplate;

    // 설명. application.yml에서 설정된 값을 주입
    @Value("${ai.server-url}")
    private String aiServerUrl;

    private static final String AI_SERVER_URL = "http://localhost:8000/query"; // AI 서버 주소

    public AIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AIResponseDTO communicateWithAI(AIRequestDTO chatbotRequest) {
        try {
            // HTTP Header 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // HTTP Body 설정
            HttpEntity<AIRequestDTO> request = new HttpEntity<>(chatbotRequest, headers);

            // AI 서버 호출
            ResponseEntity<AIResponseDTO> response = restTemplate.postForEntity(
                    aiServerUrl,  // yml에서 읽은 URL 사용
                    request,
                    AIResponseDTO.class
            );

            // 응답 반환
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("AI 서버와 통신 중 오류 발생: " + e.getMessage());
        }
    }
}