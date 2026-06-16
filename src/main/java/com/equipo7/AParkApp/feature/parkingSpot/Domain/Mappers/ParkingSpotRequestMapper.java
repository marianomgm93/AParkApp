package com.equipo7.AParkApp.feature.parkingSpot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotRequest;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParkingSpotRequestMapper  {


    private final ModelMapper mapper;

    public ParkingSpotEntity toEntity(ParkingSpotRequest dto) {

        return ParkingSpotEntity.builder()
                .name(dto.getName())
                .active(dto.isActive())
                .number(dto.getNumber())
                .build();
    }


    public ParkingSpotRequest toDto(ParkingSpotEntity entity) {


        return mapper.map(entity,ParkingSpotRequest.class);

    }

}
