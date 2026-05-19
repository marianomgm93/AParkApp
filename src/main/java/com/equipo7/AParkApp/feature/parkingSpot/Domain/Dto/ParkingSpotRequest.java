package com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingSpotRequest {

        @NotBlank
        private String name;

        @NotNull
        private UUID parkingLotId;

        private Boolean status;

        private boolean active;

        @Positive
        private int number;
    }


