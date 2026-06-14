package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;

import java.util.List;
import java.util.UUID;

public interface IVehicleService {
    VehicleDTO save(NewVehicleDTO newVehicleDTO);
    void delete(UUID vehicleId);
    VehicleDTO update(UUID vehicleId, NewVehicleDTO newVehicleDTO);
    VehicleDTO findById(UUID vehicleId);
    List<VehicleDTO> findAll();
}
