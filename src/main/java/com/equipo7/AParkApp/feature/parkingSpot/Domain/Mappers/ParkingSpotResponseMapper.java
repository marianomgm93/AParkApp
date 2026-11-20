package com.equipo7.AParkApp.feature.parkingSpot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotResponse;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParkingSpotResponseMapper {

    private final ModelMapper mapper;


    public ParkingSpotEntity toEntity( ParkingSpotResponse dto) {


        return mapper.map(dto,ParkingSpotEntity.class);

    }

    public ParkingSpotResponse toDto(ParkingSpotEntity entity) {
        ParkingSpotResponse dto = mapper.map(entity, ParkingSpotResponse.class);
        if (entity != null && entity.getParkingLot() != null) {
            dto.setParkingLotId(entity.getParkingLot().getId());
            dto.setParkingLotName(entity.getParkingLot().getName());
        }
        return dto;
    }

}
