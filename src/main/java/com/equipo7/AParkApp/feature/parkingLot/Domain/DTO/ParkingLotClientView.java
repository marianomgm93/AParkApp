package com.equipo7.AParkApp.feature.parkingLot.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotClientView {

    private String name;
    private String street;
    private Integer number;
    private String zipCode;
    private int capacity;
}
