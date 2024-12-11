package com.pado.inflow.chatbot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "session_history")
public class SessionHistory {

    @Id
    @Column(name = "session_history_id", nullable = false, length = 255)
    private String sessionHistoryId;

    @Column(name = "chatbot_type", nullable = false)
    private String chatbotType;

    @Column(name = "chatbot_content", columnDefinition = "TEXT")
    private String chatbotContent;

    @Column(name = "selected_keyword")
    private String selectedKeyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private ChatbotSession chatbotSession;
}
