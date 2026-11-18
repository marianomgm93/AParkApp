package com.equipo7.AParkApp.feature.parkingLot.Domain.Mappers;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
import com.equipo7.AParkApp.feature.parkingLot.Domain.ParkingLotEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParkingLotResponseMapper {


    private final ModelMapper mapper;

    public ParkingLotEntity toEntity(ParkingLotResponse dto) {

        return mapper.map(dto, ParkingLotEntity.class);
    }

    public ParkingLotResponse toDTO(ParkingLotEntity entity) {
        ParkingLotResponse dto = mapper.map(entity, ParkingLotResponse.class);
        if (entity.getOwner() != null) {
            dto.setOwnerId(entity.getOwner().getId());
            dto.setOwnerUsername(entity.getOwner().getName());
        }
        if (entity.getAddress() != null) {
            dto.setAddressId(entity.getAddress().getId());
            dto.setStreet(entity.getAddress().getStreet());
            dto.setNumber(entity.getAddress().getNumber());
            dto.setZipCode(entity.getAddress().getZipCode());
            dto.setNotes(entity.getAddress().getNotes());
        }

        return dto;
    }
}
