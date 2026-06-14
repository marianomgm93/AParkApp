package com.equipo7.AParkApp.feature.parkingSpot;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotRequest;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotResponse;

import java.util.List;
import java.util.UUID;

public interface IParkingSpotService {

    ParkingSpotResponse createParkingSpot(ParkingSpotRequest parkingSpotRequest);
    List<ParkingSpotResponse> getAllParkingSpots();
    ParkingSpotResponse getParkingSpotById(UUID id);
    ParkingSpotResponse updateParkingSpot(UUID id, ParkingSpotRequest parkingSpotRequest);
    void deleteParkingSpotById(UUID id);
    void restoreParkingSpot(UUID id);
    void occupy(UUID id);
    void release(UUID id);

}
