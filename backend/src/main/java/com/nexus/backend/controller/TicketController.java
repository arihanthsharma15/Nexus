package com.nexus.backend.controller;

import com.nexus.backend.entity.Ticket;
import com.nexus.backend.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Create a new ticket
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    // Get all tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // Get ticket by ID
    @GetMapping("/{id}")
    public Optional<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }
}
