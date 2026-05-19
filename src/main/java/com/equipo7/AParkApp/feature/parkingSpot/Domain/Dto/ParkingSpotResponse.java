package com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto;

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

    private Boolean status;

    private Boolean active;

    private int number;
}