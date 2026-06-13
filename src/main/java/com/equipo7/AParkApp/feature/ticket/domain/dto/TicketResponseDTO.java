package com.equipo7.AParkApp.feature.ticket.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TicketResponseDTO(
        UUID id,
        UUID reservationId,
        BigDecimal amount,
        BigDecimal paid,
        LocalDateTime timeStamp,
        String note) {
}
