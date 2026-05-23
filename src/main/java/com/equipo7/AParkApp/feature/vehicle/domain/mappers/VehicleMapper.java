package com.equipo7.AParkApp.feature.vehicle.domain.mappers;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.vehicle.domain.VehicleEntity;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMapper implements IMapper<VehicleEntity, VehicleDTO> {

    private final ModelMapper mapper;

    public VehicleDTO toDTO(VehicleEntity vehicleEntity) {
        return mapper.map(vehicleEntity, VehicleDTO.class);
    }

    public VehicleEntity toEntity(VehicleDTO vehicleDTO) {
        return mapper.map(vehicleDTO, VehicleEntity.class);
    }
}
