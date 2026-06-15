package com.equipo7.AParkApp.feature.offer.domain.dto;

import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import com.equipo7.AParkApp.feature.stay.StayType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
public record OfferRequestDTO(

        @NotNull
        UUID parkingLotId,

        UUID parkingSpotId,

        @NotNull
        StayType stayType,

        @NotNull
        @FutureOrPresent
        LocalDateTime startTime,

        @NotNull
        @Future
        LocalDateTime endTime

) {
}