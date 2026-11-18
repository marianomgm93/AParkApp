package com.equipo7.AParkApp.feature.parkingLot.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingLotResponse {

    private UUID id;
    private String name;
    private UUID ownerId;
    private String ownerUsername;
    private UUID addressId;
    private String street;
    private Integer number;
    private String zipCode;
    private String notes;
    private int capacity;
    private boolean active;

}





