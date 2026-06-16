package com.equipo7.AParkApp.feature.vehicle.domain.mappers;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.vehicle.VehicleEntity;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewVehicleMapper implements IMapper<VehicleEntity, NewVehicleDTO> {

    private final ModelMapper mapper;

    public VehicleEntity toEntity(NewVehicleDTO newVehicleDTO) {
        return VehicleEntity.builder()
                .plate(newVehicleDTO.getPlate())
                .model(newVehicleDTO.getModel())
                .color(newVehicleDTO.getColor())
                .note(newVehicleDTO.getNote())
                .vehicleType(newVehicleDTO.getVehicleType())
                .brand(newVehicleDTO.getBrand())
                .build();
    }

    public NewVehicleDTO toDTO(VehicleEntity vehicleEntity) {
        return NewVehicleDTO.builder()
                .plate(vehicleEntity.getPlate())
                .userId(
                        vehicleEntity.getUser() != null
                                ? vehicleEntity.getUser().getId()
                                : null)
                .model(vehicleEntity.getModel())
                .color(vehicleEntity.getColor())
                .note(vehicleEntity.getNote())
                .brand(vehicleEntity.getBrand())
                .vehicleType(
                        vehicleEntity.getVehicleType())
                .build();
    }
}
