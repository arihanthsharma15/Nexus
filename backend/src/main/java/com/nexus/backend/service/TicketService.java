package com.nexus.backend.service;

import com.nexus.backend.entity.Ticket;
import com.nexus.backend.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Create a new ticket
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // Retrieve all tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }
}
