package com.equipo7.AParkApp.feature.reservation.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationRequestDTO(

        @NotNull
        @Future
        LocalDateTime startTime,

        @Future
        LocalDateTime endTime,

        @NotNull
        UUID parkingLotId,

        UUID parkingSpotId,

        @NotNull
        UUID vehicleId,

        @NotNull
        UUID offerId,

        @NotNull
        UUID userId

) {
}
