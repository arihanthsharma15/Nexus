package com.nexus.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class TicketCommentRequest {

    @NotBlank(message = "Comment content cannot be blank")
    private String content;

    public TicketCommentRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
