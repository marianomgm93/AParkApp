package com.equipo7.AParkApp.feature.parkingSpot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotRequest;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.ParkingSpotEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingSpotRequestMapper extends IMapper<ParkingSpotEntity,ParkingSpotRequest> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public ParkingSpotEntity toEntity(ParkingSpotRequest dto) {


    return mapper.map(dto,ParkingSpotEntity.class);
    }


    @Override
    public ParkingSpotRequest toDto(ParkingSpotEntity entity) {


        return mapper.map(entity,ParkingSpotRequest.class);

    }

}
