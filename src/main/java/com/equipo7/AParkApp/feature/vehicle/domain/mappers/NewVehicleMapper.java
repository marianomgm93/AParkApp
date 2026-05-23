package com.equipo7.AParkApp.feature.vehicle.domain.mappers;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.vehicle.domain.VehicleEntity;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewVehicleMapper implements IMapper<VehicleEntity, NewVehicleDTO> {

    private final ModelMapper mapper;

    public VehicleEntity toEntity(NewVehicleDTO newVehicleDTO) {
        return mapper.map(newVehicleDTO, VehicleEntity.class);
    }

    public NewVehicleDTO toDTO(VehicleEntity vehicleEntity) {
        return mapper.map(vehicleEntity, NewVehicleDTO.class);
    }
}
