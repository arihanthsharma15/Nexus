package com.nexus.backend.service;

import com.nexus.backend.entity.Ticket;
import com.nexus.backend.entity.TicketComment;
import com.nexus.backend.exception.TicketNotFoundException;
import com.nexus.backend.repository.TicketCommentRepository;
import com.nexus.backend.repository.TicketRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketCommentServiceTest {

    @Mock
    private TicketCommentRepository ticketCommentRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketCommentService ticketCommentService;

    @Test
    void addCommentSuccessfully() {

        Long ticketId = 2L;
        String content = "Internet is not working";

        Ticket ticket = mock(Ticket.class);

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        when(ticketCommentRepository.save(any(TicketComment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TicketComment result =
                ticketCommentService.addComment(ticketId, content);

        assertNotNull(result);
        assertEquals(content, result.getContent());
        assertSame(ticket, result.getTicket());

        verify(ticketRepository).findById(ticketId);
        verify(ticketCommentRepository).save(any(TicketComment.class));
    }

    @Test
    void getCommentsSuccessfully() {

        Long ticketId = 2L;

        TicketComment comment = new TicketComment();
        comment.setContent("Internet is not working");

        List<TicketComment> comments = List.of(comment);

        when(ticketRepository.existsById(ticketId))
                .thenReturn(true);

        when(ticketCommentRepository
                .findByTicketIdOrderByCreatedAtAsc(ticketId))
                .thenReturn(comments);

        List<TicketComment> result =
                ticketCommentService.getComments(ticketId);

        assertEquals(1, result.size());
        assertEquals(
                "Internet is not working",
                result.get(0).getContent()
        );

        verify(ticketRepository).existsById(ticketId);

        verify(ticketCommentRepository)
                .findByTicketIdOrderByCreatedAtAsc(ticketId);
    }

    @Test
    void addCommentToNonExistingTicket() {

        Long ticketId = 999L;

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.empty());

        assertThrows(
                TicketNotFoundException.class,
                () -> ticketCommentService.addComment(
                        ticketId,
                        "Test comment"
                )
        );

        verify(ticketCommentRepository, never())
                .save(any(TicketComment.class));
    }

    @Test
    void getCommentsForNonExistingTicket() {

        Long ticketId = 999L;

        when(ticketRepository.existsById(ticketId))
                .thenReturn(false);

        assertThrows(
                TicketNotFoundException.class,
                () -> ticketCommentService.getComments(ticketId)
        );

        verify(ticketCommentRepository, never())
                .findByTicketIdOrderByCreatedAtAsc(ticketId);
    }
}
