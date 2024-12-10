package com.pado.inflow.chatbot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "chatbot_session")
public class ChatbotSession {

    @Id
    @Column(name = "session_id", nullable = false, length = 255)
    private String sessionId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "first_question", nullable = false, length = 255)
    private String firstQuestion;

    @OneToMany(mappedBy = "chatbotSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionHistory> sessionHistories;
}
