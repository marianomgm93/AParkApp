package com.equipo7.AParkApp.feature.reservation.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationUpdateRequest(
        @NotNull(message = "Start time Mustn't be Null")
        @Future(message = "Start time Must be in the future!")
        LocalDateTime startTime,

        @Future(message = "End time Must be in the future!")
        LocalDateTime endTime,

        @NotNull(message = "Parking Lot id Mustn't be Null")
        UUID parkingSpotId,

        @NotNull(message = "Vehicle Id mustn't be null")
        UUID vehicleId

) {
}
