package com.equipo7.AParkApp.feature.parkingLot.Domain.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingLotClientView {

    private String name;
    private String street;
    private Integer number;
    private String zipCode;
    private int capacity;
}
