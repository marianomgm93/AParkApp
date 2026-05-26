package com.equipo7.AParkApp.feature.vehicle.domain.mappers;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.vehicle.Type.VehicleTypeEntity;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleTypeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class NewVehicleTypeMapper implements IMapper<VehicleTypeEntity, NewVehicleTypeDTO> {
    private final ModelMapper mapper;

    @Override
    public VehicleTypeEntity toEntity(NewVehicleTypeDTO newVehicleTypeDTO) {
        return mapper.map(newVehicleTypeDTO, VehicleTypeEntity.class);
    }

    @Override
    public NewVehicleTypeDTO toDTO(VehicleTypeEntity vehicleTypeEntity) {
        return mapper.map(vehicleTypeEntity, NewVehicleTypeDTO.class);
    }
}
