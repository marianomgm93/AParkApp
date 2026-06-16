package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEntity;
import com.equipo7.AParkApp.feature.VehicleType.VehicleTypeEnum;
import com.equipo7.AParkApp.feature.stay.StayType;
import com.equipo7.AParkApp.feature.stay.StayTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PriceRepository extends JpaRepository<PriceEntity, UUID> {

    Optional<PriceEntity> findByVehicleTypeAndStayType(VehicleTypeEnum vehicleType, StayType stayType);
}
