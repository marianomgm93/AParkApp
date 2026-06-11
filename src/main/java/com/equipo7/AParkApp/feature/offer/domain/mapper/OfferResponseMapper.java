package com.equipo7.AParkApp.feature.offer.domain.mapper;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.offer.OfferEntity;
import com.equipo7.AParkApp.feature.offer.domain.dto.OfferResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class OfferResponseMapper implements IMapper<OfferEntity, OfferResponseDTO> {

    @Override
    public OfferEntity toEntity(OfferResponseDTO offerResponseDTO) {
        return OfferEntity.builder()
                .startTime(offerResponseDTO.startTime())
                .endTime(offerResponseDTO.endTime())
                .build();
    }

    @Override
    public OfferResponseDTO toDTO(OfferEntity offerEntity) {
        return OfferResponseDTO.builder()
                .parkingLotId(offerEntity.getParkingLot().getId())
                .parkingSpotId(offerEntity.getParkingSpot().getId())
                .startTime(offerEntity.getStartTime())
                .endTime(offerEntity.getEndTime())
                .build();
    }
}
