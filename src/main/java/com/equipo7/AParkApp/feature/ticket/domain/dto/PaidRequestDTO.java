package com.equipo7.AParkApp.feature.ticket.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record PaidRequestDTO(
        @NotNull(message = "Invalid id")
        UUID id,
        @Positive(message = "Amount must be positive")
        BigDecimal pay
) {
}
