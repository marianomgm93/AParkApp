package com.equipo7.AParkApp.feature.parkingLot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class ParkingLotResponseMapper implements IMapper<ParkingLotEntity,ParkingLotResponse> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public ParkingLotEntity toEntity(ParkingLotResponse dto)
    {

        return mapper.map(dto, ParkingLotEntity.class);
    }

    @Override
    public ParkingLotResponse toDTO(ParkingLotEntity entity)
    {

        return mapper.map(entity, ParkingLotResponse.class);
    }
}
