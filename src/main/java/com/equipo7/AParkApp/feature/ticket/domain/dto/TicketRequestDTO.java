package com.equipo7.AParkApp.feature.ticket.domain.dto;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.reservation.ReservationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TicketRequestDTO(
        @NotNull(message = "Invalid reservation")
        UUID reservationId,
        @PositiveOrZero(message = "Amount must be positive")
        BigDecimal amount,
        @PositiveOrZero(message = "Amount must be positive")
        BigDecimal paid,
        @NotNull(message = "Invalid date-time")
        LocalDateTime timeStamp,
        String note) {
}
