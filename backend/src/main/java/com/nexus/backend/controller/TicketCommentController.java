package com.nexus.backend.controller;

import com.nexus.backend.dto.TicketCommentRequest;
import com.nexus.backend.dto.TicketCommentResponse;
import com.nexus.backend.entity.TicketComment;
import com.nexus.backend.service.TicketCommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets/{ticketId}/comments")
public class TicketCommentController {

    private final TicketCommentService ticketCommentService;

    public TicketCommentController(
            TicketCommentService ticketCommentService) {
        this.ticketCommentService = ticketCommentService;
    }

    @PostMapping
    public ResponseEntity<TicketCommentResponse> addComment(
            @PathVariable Long ticketId,
            @Valid @RequestBody TicketCommentRequest request) {

        TicketComment comment = ticketCommentService.addComment(
                ticketId,
                request.getContent()
        );

        TicketCommentResponse response = new TicketCommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getTicket().getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketCommentResponse>> getComments(
            @PathVariable Long ticketId) {

        List<TicketCommentResponse> responses =
                ticketCommentService.getComments(ticketId)
                        .stream()
                        .map(comment -> new TicketCommentResponse(
                                comment.getId(),
                                comment.getContent(),
                                comment.getCreatedAt(),
                                comment.getTicket().getId()
                        ))
                        .toList();

        return ResponseEntity.ok(responses);
    }
}
