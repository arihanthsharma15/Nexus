package com.nexus.backend.dto;

import java.time.LocalDateTime;

public class TicketCommentResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long ticketId;

    public TicketCommentResponse(
            Long id,
            String content,
            LocalDateTime createdAt,
            Long ticketId) {

        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.ticketId = ticketId;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getTicketId() {
        return ticketId;
    }
}
