package com.equipo7.AParkApp.feature.offer.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
public record OfferResponseDTO (
        UUID parkingLotId,
        UUID parkingSpotId,
        LocalDateTime startTime,
        LocalDateTime endTime){
}
