package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;

import java.util.List;
import java.util.UUID;

public class ParkingLotService implements IParkingLotService {


    @Override
    public List<ParkingLotResponse> getAllParkingLots() {
        return List.of();
    }

    @Override
    public ParkingLotResponse getParkingLotById(UUID Id) {
        return null;
    }

    @Override
    public ParkingLotResponse save(ParkingLotRequest parkingLotRequest) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
