package com.equipo7.AParkApp.feature.reservation.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationUpdateRequest (
        @NotNull
        @Future
        LocalDateTime startTime,

        @Future
        LocalDateTime endTime,

        UUID parkingSpotId,

        @NotNull
        UUID vehicleId

){
}
