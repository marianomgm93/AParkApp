package com.equipo7.AParkApp.feature.parkingSpot;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IParkingSpotRepository extends JpaRepository<ParkingSpotEntity,UUID> {

    List<ParkingSpotEntity> findByStatusTrue();
    Optional <ParkingSpotEntity> findByIdAndActiveTrue(UUID id);

    List<ParkingSpotEntity> findByParkingLotIdAndActiveTrue(UUID parkingLotId);


}
