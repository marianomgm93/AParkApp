package com.equipo7.AParkApp.feature.offer.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AcquireOfferRequest(@NotNull(message = "User id must be valid") UUID userId, @NotNull(message = "Vehicle id must be valid") UUID vehicleId) {
}
