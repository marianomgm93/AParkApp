package com.equipo7.AParkApp.feature.parkingSpot;

public class ParkingSpotUnavailableException extends RuntimeException {
    public ParkingSpotUnavailableException(String message) {
        super(message);
    }
}
