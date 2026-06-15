package com.equipo7.AParkApp.feature.reservation.domain.dto;

import com.equipo7.AParkApp.feature.reservation.ReservationStatus;
import com.equipo7.AParkApp.feature.stay.StayType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationResponseDTO (
    LocalDateTime startTime,
    LocalDateTime endTime,
    UUID parkingLotId,
    UUID parkingSpotId,
    UUID vehicleId,
    UUID offerId,
    UUID userId,
    String stayType,
    String status
){}

