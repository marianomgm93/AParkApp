package com.equipo7.AParkApp.feature.vehicle.domain.mappers;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.vehicle.domain.VehicleEntity;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleMapper extends IMapper<VehicleEntity, VehicleDTO> {

    VehicleDTO toDTO(VehicleEntity vehicleEntity);
    VehicleEntity toEntity(VehicleDTO vehicleDTO);
}
