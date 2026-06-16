package com.equipo7.AParkApp.feature.offer.domain.mapper;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.offer.OfferEntity;
import com.equipo7.AParkApp.feature.offer.domain.dto.OfferResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class OfferResponseMapper implements IMapper<OfferEntity, OfferResponseDTO> {

    @Override
    public OfferEntity toEntity(OfferResponseDTO dto) {
        throw new UnsupportedOperationException(
                "OfferResponseDTO cannot be converted to OfferEntity");
    }

    @Override
    public OfferResponseDTO toDTO(OfferEntity offer) {

        return OfferResponseDTO.builder()
                .id(offer.getId())
                .parkingLotId(offer.getParkingLot().getId())
                .parkingSpotId(
                        offer.getParkingSpot() != null
                                ? offer.getParkingSpot().getId()
                                : null)
                .stayType(offer.getStayType())
                .startTime(offer.getStartTime())
                .endTime(offer.getEndTime())
                .active(offer.isActive())
                .build();
    }

}
