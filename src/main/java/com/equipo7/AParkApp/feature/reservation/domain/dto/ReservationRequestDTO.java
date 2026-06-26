package com.equipo7.AParkApp.feature.reservation.domain.dto;

import com.equipo7.AParkApp.feature.stay.StayType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationRequestDTO(

        @NotNull(message = "You must insert a StartTime")
        @Future(message = "The start time Must be in the future!")
        LocalDateTime startTime,

        @Future(message = "the end time must be in the future!")
        LocalDateTime endTime,

        @NotNull(message = "Parking lot ID mustn't be null")
        UUID parkingLotId,

        @NotNull(message = "Parking lot ID mustn't be null")
        UUID parkingSpotId,

        @NotNull(message = "Vehicle Id mustn't be null")
        UUID vehicleId,

        @NotNull(message = "Offer Id mustn't be null")
        UUID offerId,

        @NotNull(message = "Stay type mustn't be null")
        StayType stayType,

        @NotNull(message = "User Id mustn't be null")
        UUID userId

) {
}
