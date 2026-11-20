package com.equipo7.AParkApp.feature.ticket.domain.dto;

import com.equipo7.AParkApp.feature.ticket.TicketStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TicketResponseDTO(
        UUID id,
        UUID reservationId,
        BigDecimal amount,
        BigDecimal paid,
        LocalDateTime timeStamp,
        String note,
        String parkingLotName,
        TicketStatus status) {
}
