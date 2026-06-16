package com.arsw.turnos.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Ticket {
    private String id;
    private String service;
    private TicketStatus status;
    private LocalDateTime createdAt;
}
