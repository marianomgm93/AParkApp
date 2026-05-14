package com.equipo7.AParkApp.feature.vehicle.domain.mappers;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.vehicle.domain.VehicleEntity;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewVehicleMapper extends IMapper<VehicleEntity, NewVehicleDTO> {

    VehicleEntity toEntity(NewVehicleDTO newVehicleDTO);
    NewVehicleDTO toDTO(VehicleEntity vehicleEntity);
}
