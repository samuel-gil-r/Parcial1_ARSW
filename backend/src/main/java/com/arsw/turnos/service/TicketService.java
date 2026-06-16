package com.arsw.turnos.service;

import com.arsw.turnos.model.Ticket;
import com.arsw.turnos.model.TicketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ConcurrentHashMap<String, Ticket> tickets = new ConcurrentHashMap<>();

    public Ticket createTicket(String service) {
        Ticket ticket = Ticket.builder()
                .id(UUID.randomUUID().toString())
                .service(service)
                .status(TicketStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public Optional<Ticket> callNextTicket() {
        Optional<Ticket> next = tickets.values().stream()
                .filter(t -> t.getStatus() == TicketStatus.CREATED)
                .min((a, b) -> a.getCreatedAt().compareTo(b.getCreatedAt()));

        next.ifPresent(ticket -> {
            ticket.setStatus(TicketStatus.CALLED);
            messagingTemplate.convertAndSend("/topic/tickets", ticket);
        });

        return next;
    }
}
