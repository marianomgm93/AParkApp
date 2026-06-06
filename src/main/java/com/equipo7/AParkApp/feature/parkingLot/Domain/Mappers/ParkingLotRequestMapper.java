package com.equipo7.AParkApp.feature.parkingLot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParkingLotRequestMapper  {

    private final ModelMapper mapper;

    public ParkingLotEntity toEntity(ParkingLotRequest dto)
    {

        return mapper.map(dto, ParkingLotEntity.class);
    }

    public ParkingLotRequest toDTO(ParkingLotEntity entity)
    {

        return mapper.map(entity, ParkingLotRequest.class);
    }
}


