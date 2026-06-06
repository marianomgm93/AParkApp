package com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull
    private boolean active;

    @Positive
    private int number;
}


