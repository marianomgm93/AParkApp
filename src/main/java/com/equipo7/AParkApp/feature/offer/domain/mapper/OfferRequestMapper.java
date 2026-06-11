package com.equipo7.AParkApp.feature.offer.domain.mapper;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.offer.OfferEntity;
import com.equipo7.AParkApp.feature.offer.domain.dto.OfferRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class OfferRequestMapper implements IMapper<OfferEntity, OfferRequestDTO> {

    @Override
    public OfferEntity toEntity(OfferRequestDTO requestDTO) {
        return OfferEntity.builder()
                .startTime(requestDTO.startTime())
                .endTime(requestDTO.endTime())
                .build();
    }

    @Override
    public OfferRequestDTO toDTO(OfferEntity offerEntity) {
        return OfferRequestDTO.builder()
                .parkingSpotId(offerEntity.getParkingSpot().getId())
                .parkingLotId(offerEntity.getParkingLot().getId())
                .startTime(offerEntity.getStartTime())
                .endTime(offerEntity.getEndTime())
                .build();
    }
}
