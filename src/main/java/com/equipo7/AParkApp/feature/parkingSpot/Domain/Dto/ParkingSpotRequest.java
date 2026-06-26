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

    @NotBlank(message = "You Must enter a Name")
    private String name;

    @NotNull(message = "You Must enter a valid parking spot ID")
    private UUID parkingLotId;

    @NotNull
    private boolean active;

    @Positive(message = "You must enter a valid number")
    private int number;
}


