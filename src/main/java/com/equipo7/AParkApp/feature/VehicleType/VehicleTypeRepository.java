package com.equipo7.AParkApp.feature.VehicleType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity, UUID> {
}
