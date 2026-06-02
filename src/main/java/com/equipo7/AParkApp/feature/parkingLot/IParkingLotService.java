package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;

import java.util.List;
import java.util.UUID;


public interface IParkingLotService {

    ParkingLotResponse create(ParkingLotRequest request);
    List <ParkingLotResponse> getAllParkingLots();
    ParkingLotResponse getParkingLotById(UUID id);
    ParkingLotResponse update(UUID id, ParkingLotRequest request);
    void delete(UUID id);
    void restore(UUID id);
    List <ParkingLotResponse> getAllActiveParkingLots();
}
