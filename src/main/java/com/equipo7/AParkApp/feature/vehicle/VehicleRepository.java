package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.driver.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID> {

    Optional<VehicleEntity> findByPlate(String plate);

}
