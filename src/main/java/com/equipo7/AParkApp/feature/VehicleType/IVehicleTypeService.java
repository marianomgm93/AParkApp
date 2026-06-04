package com.equipo7.AParkApp.feature.VehicleType;

import com.equipo7.AParkApp.feature.VehicleType.domain.dto.VehicleTypeDTO;

import java.util.List;
import java.util.UUID;

public interface IVehicleTypeService {
    VehicleTypeDTO save(VehicleTypeDTO vehicleTypeDTO);
    void delete(UUID vehicleTypeId);
    VehicleTypeDTO update(UUID vehicleTypeId, VehicleTypeDTO vehicleTypeDTO);
    VehicleTypeDTO findById(UUID vehicleTypeId);
    List<VehicleTypeDTO> findAll();

}
