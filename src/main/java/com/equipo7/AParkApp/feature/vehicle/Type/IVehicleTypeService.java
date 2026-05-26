package com.equipo7.AParkApp.feature.vehicle.Type;

import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleTypeDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleTypeDTO;

import java.util.List;
import java.util.UUID;

public interface IVehicleTypeService {
    VehicleTypeDTO save(NewVehicleTypeDTO newVehicleTypeDTO);
    void delete(UUID vehicleTypeId);
    VehicleTypeDTO update(UUID vehicleTypeId, NewVehicleTypeDTO newVehicleTypeDTO);
    VehicleTypeDTO findById(UUID vehicleTypeId);
    List<VehicleTypeDTO> findAll();
}
