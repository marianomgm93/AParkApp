package com.equipo7.AParkApp.feature.parkingSpot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotResponse;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotResponseMapper extends IMapper<ParkingSpotEntity, ParkingSpotResponse> {

    @Autowired
    private Modelmapper mapper;


    @Override
    public ParkingSpotEntity toEntity( ParkingSpotResponse dto) {


        return mapper.map(dto,ParkingSpotEntity.class);

    }
    @Override
    public ParkingSpotResponse toDto(ParkingSpotEntity entity) {


        return mapper.map(entity,ParkingSpotResponse.class);
    }

}
