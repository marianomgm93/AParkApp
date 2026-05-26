package com.equipo7.AParkApp.feature.vehicle.domain.mappers;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.vehicle.Type.VehicleTypeEntity;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleTypeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleTypeMapper implements IMapper<VehicleTypeEntity, VehicleTypeDTO> {
    private final ModelMapper mapper;

    @Override
    public VehicleTypeEntity toEntity(VehicleTypeDTO vehicleTypeDTO) {
        return mapper.map(vehicleTypeDTO, VehicleTypeEntity.class);
    }

    @Override
    public VehicleTypeDTO toDTO(VehicleTypeEntity vehicleTypeEntity) {
        return mapper.map(vehicleTypeEntity, VehicleTypeDTO.class);
    }
}
