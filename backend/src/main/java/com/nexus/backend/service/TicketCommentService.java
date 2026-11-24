package com.nexus.backend.service;
import com.nexus.backend.entity.Ticket;
import com.nexus.backend.entity.TicketComment;
import com.nexus.backend.exception.TicketNotFoundException;
import com.nexus.backend.repository.TicketCommentRepository;
import com.nexus.backend.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketCommentService {

    private final TicketCommentRepository ticketCommentRepository;
    private final TicketRepository ticketRepository;

    public TicketCommentService(
            TicketCommentRepository ticketCommentRepository,
            TicketRepository ticketRepository) {

        this.ticketCommentRepository = ticketCommentRepository;
        this.ticketRepository = ticketRepository;
    }

    public TicketComment addComment(Long ticketId, String content) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new TicketNotFoundException(
                                "Ticket not found with id: " + ticketId
                        ));

        TicketComment comment = new TicketComment();
        comment.setContent(content);
        comment.setTicket(ticket);

        return ticketCommentRepository.save(comment);
    }

    public List<TicketComment> getComments(Long ticketId) {

        if (!ticketRepository.existsById(ticketId)) {
            throw new TicketNotFoundException(
                    "Ticket not found with id: " + ticketId
            );
        }

        return ticketCommentRepository
                .findByTicketIdOrderByCreatedAtAsc(ticketId);
    }
}
