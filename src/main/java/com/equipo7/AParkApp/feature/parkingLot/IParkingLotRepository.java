package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface IParkingLotRepository extends JpaRepository <ParkingLotEntity, UUID>,JpaSpecificationExecutor<ParkingLotEntity> {

    List<ParkingLotEntity> findByActiveTrue();


   Optional <ParkingLotEntity> findByIdAndActiveTrue(UUID id);
}
