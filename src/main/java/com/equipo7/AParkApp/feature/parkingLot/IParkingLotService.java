package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;

import java.util.List;
import java.util.UUID;

public interface IParkingLotService {

    List <ParkingLotResponse> getAllParkingLots();
    ParkingLotResponse getParkingLotById(UUID Id);
    ParkingLotResponse save(ParkingLotRequest parkingLotRequest);
    void delete (UUID id);


}
