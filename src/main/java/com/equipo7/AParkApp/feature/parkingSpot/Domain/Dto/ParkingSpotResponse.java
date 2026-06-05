package com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingSpotResponse {

    private UUID id;

    private String name;

    private Boolean active;

    private Status status;

    private int number;
}