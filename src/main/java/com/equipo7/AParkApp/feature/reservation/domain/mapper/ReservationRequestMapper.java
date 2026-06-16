package com.equipo7.AParkApp.feature.reservation.domain.mapper;

import com.equipo7.AParkApp.common.model.IMapper;
import com.equipo7.AParkApp.feature.reservation.ReservationEntity;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ReservationRequestMapper implements IMapper<ReservationEntity, ReservationRequestDTO> {

    @Override
    public ReservationEntity toEntity(ReservationRequestDTO reservationRequestDTO) {
        return ReservationEntity.builder()
                .startTime(reservationRequestDTO.startTime())
                .endTime(reservationRequestDTO.endTime())
        .build();
    }

    @Override
    public ReservationRequestDTO toDTO(ReservationEntity reservationEntity) {
            return new ReservationRequestDTO(
                    reservationEntity.getStartTime(),
                    reservationEntity.getEndTime(),
                    reservationEntity.getParkingLot().getId(),
                    reservationEntity.getParkingSpot().getId(),
                    reservationEntity.getVehicle().getId(),
                    reservationEntity.getOffer().getId(),
                    reservationEntity.getStayType(),
                    reservationEntity.getUser().getId()
            );
    }
}
