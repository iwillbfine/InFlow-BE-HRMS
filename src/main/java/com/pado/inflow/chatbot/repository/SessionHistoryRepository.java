package com.pado.inflow.chatbot.repository;

import com.pado.inflow.chatbot.entity.SessionHistory;
import com.pado.inflow.chatbot.dto.SessionHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionHistoryRepository extends JpaRepository<SessionHistory, String> {
    List<SessionHistory> findByChatbotSession_SessionId(String sessionId);
}
