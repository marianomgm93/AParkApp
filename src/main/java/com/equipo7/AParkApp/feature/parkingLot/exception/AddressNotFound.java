package com.equipo7.AParkApp.feature.parkingLot.exception;

public class AddressNotFound extends RuntimeException {
    public AddressNotFound(String message) {
        super(message);
    }
}
