package com.pado.inflow.chatbot.repository;

import com.pado.inflow.chatbot.entity.ChatbotSession;
import com.pado.inflow.chatbot.dto.ChatbotSessionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatbotSessionRepository extends JpaRepository<ChatbotSession, String> {
    List<ChatbotSession> findByEmployeeId(Long employeeId);
}
