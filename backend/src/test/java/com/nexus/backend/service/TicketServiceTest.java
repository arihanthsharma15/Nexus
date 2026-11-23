package com.nexus.backend.service;

import com.nexus.backend.entity.Ticket;
import com.nexus.backend.entity.TicketStatus;
import com.nexus.backend.entity.TicketPriority;
import com.nexus.backend.exception.TicketNotFoundException;
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
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    // 1. CREATE TICKET
    @Test
    void shouldCreateTicketSuccessfully() {

        Ticket ticket = new Ticket();
        ticket.setTitle("Laptop not working");
        ticket.setDescription("Laptop is not turning on");
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setPriority(TicketPriority.HIGH);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket savedTicket = ticketService.createTicket(ticket);

        assertEquals("Laptop not working", savedTicket.getTitle());
        assertEquals(TicketStatus.OPEN, savedTicket.getStatus());

        verify(ticketRepository).save(ticket);
    }

    // 2. GET ALL TICKETS
    @Test
    void shouldReturnAllTickets() {

        Ticket ticket1 = new Ticket();
        ticket1.setTitle("Laptop issue");

        Ticket ticket2 = new Ticket();
        ticket2.setTitle("Network issue");

        List<Ticket> tickets = List.of(ticket1, ticket2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.getAllTickets();

        assertEquals(2, result.size());
        assertEquals("Laptop issue", result.get(0).getTitle());
        assertEquals("Network issue", result.get(1).getTitle());

        verify(ticketRepository).findAll();
    }

    // 3. GET TICKET BY ID
    @Test
    void shouldReturnTicketById() {

        Ticket ticket = new Ticket();
        ticket.setTitle("Internet not working");

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        Ticket result = ticketService.getTicketById(1L);

        assertEquals("Internet not working", result.getTitle());

        verify(ticketRepository).findById(1L);
    }

    // 4. GET TICKET BY ID - NOT FOUND
    @Test
    void shouldThrowExceptionWhenTicketDoesNotExist() {

        when(ticketRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TicketNotFoundException.class,
                () -> ticketService.getTicketById(999L)
        );

        verify(ticketRepository).findById(999L);
    }

    // 5. UPDATE TICKET
    @Test
    void shouldUpdateTicketSuccessfully() {

        Ticket existingTicket = new Ticket();
        existingTicket.setTitle("Old title");
        existingTicket.setDescription("Old description");
        existingTicket.setStatus(TicketStatus.OPEN);
        existingTicket.setPriority(TicketPriority.LOW);

        Ticket updatedTicket = new Ticket();
        updatedTicket.setTitle("Updated title");
        updatedTicket.setDescription("Updated description");
        updatedTicket.setStatus(TicketStatus.IN_PROGRESS);
        updatedTicket.setPriority(TicketPriority.HIGH);

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(existingTicket));

        when(ticketRepository.save(existingTicket))
                .thenReturn(existingTicket);

        Ticket result = ticketService.updateTicket(1L, updatedTicket);

        assertEquals("Updated title", result.getTitle());
        assertEquals("Updated description", result.getDescription());
        assertEquals(TicketStatus.IN_PROGRESS, result.getStatus());
        assertEquals(TicketPriority.HIGH, result.getPriority());

        verify(ticketRepository).findById(1L);
        verify(ticketRepository).save(existingTicket);
    }

    // 6. UPDATE TICKET - NOT FOUND
    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingTicket() {

        Ticket updatedTicket = new Ticket();

        when(ticketRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TicketNotFoundException.class,
                () -> ticketService.updateTicket(999L, updatedTicket)
        );

        verify(ticketRepository).findById(999L);
        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    // 7. DELETE TICKET
    @Test
    void shouldDeleteTicketSuccessfully() {

        Ticket ticket = new Ticket();
        ticket.setTitle("Ticket to delete");

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        ticketService.deleteTicket(1L);

        verify(ticketRepository).findById(1L);
        verify(ticketRepository).delete(ticket);
    }

    // 8. DELETE TICKET - NOT FOUND
    @Test
    void shouldThrowExceptionWhenDeletingNonExistingTicket() {

        when(ticketRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TicketNotFoundException.class,
                () -> ticketService.deleteTicket(999L)
        );

        verify(ticketRepository).findById(999L);
        verify(ticketRepository, never()).delete(any(Ticket.class));
    }
}
