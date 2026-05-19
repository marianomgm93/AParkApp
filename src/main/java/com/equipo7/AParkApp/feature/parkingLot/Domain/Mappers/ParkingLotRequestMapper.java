package com.equipo7.AParkApp.feature.parkingLot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotRequestMapper implements IMapper<ParkingLotEntity,ParkingLotRequest> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public ParkingLotEntity toEntity(ParkingLotRequest dto)
    {

        return mapper.map(dto, ParkingLotEntity.class);
    }

    @Override
    public ParkingLotRequest toDTO(ParkingLotEntity entity)
    {

        return mapper.map(entity, ParkingLotRequest.class);
    }
}


