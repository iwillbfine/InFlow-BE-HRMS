package com.pado.inflow.chatbot.service;

import com.pado.inflow.chatbot.dto.AIRequestDTO;
import com.pado.inflow.chatbot.dto.AIResponseDTO;
import com.pado.inflow.chatbot.dto.ChatbotSessionDTO;
import com.pado.inflow.chatbot.dto.SessionHistoryDTO;
import com.pado.inflow.chatbot.entity.ChatbotSession;
import com.pado.inflow.chatbot.entity.SessionHistory;
import com.pado.inflow.chatbot.repository.ChatbotSessionRepository;
import com.pado.inflow.chatbot.repository.SessionHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AIService {

    private final ChatbotSessionRepository chatbotSessionRepository;
    private final SessionHistoryRepository sessionHistoryRepository;
    private final RestTemplate restTemplate;

    @Value("${ai.server-url}")
    private String aiServerUrl; // AI 서버 주소

    public AIService(ChatbotSessionRepository chatbotSessionRepository,
                     SessionHistoryRepository sessionHistoryRepository,
                     RestTemplate restTemplate) {
        this.chatbotSessionRepository = chatbotSessionRepository;
        this.sessionHistoryRepository = sessionHistoryRepository;
        this.restTemplate = restTemplate;
    }

    // 설명: AI 서버와 통신
    public AIResponseDTO communicateWithAI(AIRequestDTO chatbotRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.info(chatbotRequest.toString());

        HttpEntity<AIRequestDTO> request = new HttpEntity<>(chatbotRequest, headers);
        ResponseEntity<AIResponseDTO> response = restTemplate.postForEntity(aiServerUrl, request, AIResponseDTO.class);

        if (response.getBody() == null) {
            throw new RuntimeException("AI 서버와의 통신에 실패했습니다.");
        }

        // 챗봇 응답 저장
        AIResponseDTO chatbotResponse = response.getBody();

        // 대화 이력 저장 (사용자 입력)
        saveSessionHistory(chatbotRequest.getSessionId(), "HUMAN", chatbotRequest.getQuery(),null);

        // 대화 이력 저장 (챗봇 응답)
        saveSessionHistory(chatbotRequest.getSessionId(), "CHATBOT", chatbotResponse.getAnswer(),chatbotResponse.getSelectedKeyword());

        return chatbotResponse;
    }


    // 대화 이력을 저장하는 헬퍼 메서드
    private void saveSessionHistory(String sessionId, String type, String content, String selectedKeyword) {
        SessionHistory history = new SessionHistory();
        history.setSessionHistoryId("history_" + System.currentTimeMillis());
        history.setChatbotType(type);
        history.setChatbotContent(content);
        history.setSelectedKeyword(selectedKeyword);
        history.setChatbotSession(chatbotSessionRepository.findById(sessionId).orElseThrow(
                () -> new RuntimeException("챗봇 세션을 찾을 수 없습니다.")
        ));
        sessionHistoryRepository.save(history);
    }

    // 설명: 챗봇 세션이 없으면 생성
    public void ensureSessionExists(AIRequestDTO chatbotRequest) {
        String sessionId = chatbotRequest.getSessionId();

        if (!chatbotSessionRepository.existsById(sessionId)) {
            // 세션 생성
            ChatbotSession session = new ChatbotSession();
            session.setSessionId(sessionId);
            session.setEmployeeId(Long.parseLong(chatbotRequest.getEmployeeId()));
            session.setCreatedAt(LocalDateTime.now().withNano(0));
            String firstQuestion = chatbotRequest.getQuery();
            session.setFirstQuestion(firstQuestion.length() > 15 ? firstQuestion.substring(0, 15) : firstQuestion);
            chatbotSessionRepository.save(session);
        }
    }


    // 설명: 사원의 챗봇 세션 리스트 조회
    public List<ChatbotSessionDTO> getChatbotSessions(Long employeeId) {
        List<ChatbotSession> sessions = chatbotSessionRepository.findByEmployeeId(employeeId);

        // 엔티티를 DTO로 변환
        return sessions.stream()
                .map(this::convertToChatbotSessionDTO)
                .collect(Collectors.toList());
    }

    // 설명: 특정 세션의 대화 이력 조회
    public List<SessionHistoryDTO> getSessionHistory(String sessionId) {
        List<SessionHistory> histories = sessionHistoryRepository.findByChatbotSession_SessionId(sessionId);

        // 엔티티를 DTO로 변환
        return histories.stream()
                .map(this::convertToSessionHistoryDTO)
                .collect(Collectors.toList());
    }

    // 엔티티 -> ChatbotSessionDTO 변환 메서드
    private ChatbotSessionDTO convertToChatbotSessionDTO(ChatbotSession session) {
        ChatbotSessionDTO dto = new ChatbotSessionDTO();
        dto.setSessionId(session.getSessionId());
        dto.setEmployeeId(session.getEmployeeId());
        dto.setCreatedAt(session.getCreatedAt());
        dto.setFirstQuestion(session.getFirstQuestion());
        return dto;
    }

    // 엔티티 -> SessionHistoryDTO 변환 메서드
    private SessionHistoryDTO convertToSessionHistoryDTO(SessionHistory history) {
        SessionHistoryDTO dto = new SessionHistoryDTO();
        dto.setSessionHistoryId(history.getSessionHistoryId());
        dto.setChatbotType(history.getChatbotType());
        dto.setChatbotContent(history.getChatbotContent());
        dto.setSelectedKeyword(history.getSelectedKeyword());
        return dto;
    }
}
