package com.arsw.turnos.controller;

import com.arsw.turnos.model.dto.CreateTicketRequest;
import com.arsw.turnos.model.Ticket;
import com.arsw.turnos.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody CreateTicketRequest request) {
        Ticket ticket = ticketService.createTicket(request.getService());
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/next")
    public ResponseEntity<Ticket> callNext() {
        return ticketService.callNextTicket()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
