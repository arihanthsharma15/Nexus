package com.nexus.backend.service;

import com.nexus.backend.entity.Ticket;
import com.nexus.backend.repository.TicketRepository;
import com.nexus.backend.exception.TicketNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

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

    // Retrieve ticket by ID
    public Ticket getTicketById(Long id) {

        return ticketRepository.findById(id)
                .orElseThrow(() ->
                        new TicketNotFoundException(
                                "Ticket not found with ID: " + id));
    }

    // Update an existing ticket
    public Ticket updateTicket(Long id, Ticket updatedTicket) {

        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new TicketNotFoundException(
                                "Ticket not found with ID: " + id));

        existingTicket.setTitle(updatedTicket.getTitle());
        existingTicket.setDescription(updatedTicket.getDescription());
        existingTicket.setStatus(updatedTicket.getStatus());
        existingTicket.setPriority(updatedTicket.getPriority());

        return ticketRepository.save(existingTicket);
    }

    // Delete an existing ticket
    public void deleteTicket(Long id) {

        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new TicketNotFoundException(
                                "Ticket not found with ID: " + id));

        ticketRepository.delete(existingTicket);
    }
}
