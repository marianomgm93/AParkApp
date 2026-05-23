package com.equipo7.AParkApp.feature.parkingLot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
@RequiredArgsConstructor
public class ParkingLotResponseMapper{


    private final ModelMapper mapper;

    public ParkingLotEntity toEntity(ParkingLotResponse dto)
    {

        return mapper.map(dto, ParkingLotEntity.class);
    }

    public ParkingLotResponse toDTO(ParkingLotEntity entity)
    {

        return mapper.map(entity, ParkingLotResponse.class);
    }
}
